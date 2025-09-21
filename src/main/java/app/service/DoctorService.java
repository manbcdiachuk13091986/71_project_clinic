package app.service;

import app.domain.Doctor;
import app.domain.Pat;
import app.repository.DoctorRepository;
import app.exceptions.*;
import java.util.List;
import java.io.IOException;

public class DoctorService {
    private final DoctorRepository repository;
    private final PatService patService;


    public DoctorService() throws IOException {
        repository = new DoctorRepository();
        patService = new PatService();
    }


    public Doctor save(Doctor doctor) throws IOException, DoctorSaveException {
        if (doctor == null) {
            throw new DoctorSaveException("Доктор не может быть null");
        }

        String name = doctor.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new DoctorSaveException("Имя доктора не может быть пустым");
        }

        doctor.setActive(true);
        return repository.save(doctor);
    }


    public List<Doctor> getAllActiveDoctors() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Doctor::isActive)
                .toList();
    }


    public Doctor getActiveDoctorById(int id) throws IOException, DoctorNotFoundException {
       Doctor doctor = repository.findById(id);

        if (doctor == null || !doctor.isActive()) {
            throw new DoctorNotFoundException(id);
        }

        return doctor;
    }


    public void update(Doctor doctor) throws DoctorUpdateException, IOException {
        if (doctor == null) {
            throw new DoctorUpdateException("Доктор не может быть null");
        }

        String name = doctor.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new DoctorUpdateException("Имя доктора не может быть пустым");
        }

        doctor.setActive(true);
        repository.update(doctor);
    }


    public void deleteById(int id) throws IOException, DoctorNotFoundException {
      Doctor doctor = getActiveDoctorById(id);
        doctor.setActive(false);
        repository.update(doctor);
    }


    public void deleteByName(String name) throws IOException, DoctorNotFoundException {
       Doctor doctor = getAllActiveDoctors()
                .stream()
                .filter(x -> x.getName().equals(name))
                .peek(x -> x.setActive(false))
                .findFirst()
                .orElseThrow(
                        () -> new DoctorNotFoundException(name)
                );
        repository.update(doctor);
    }


    public void restoreById(int id) throws IOException, DoctorNotFoundException {
      Doctor doctor = repository.findById(id);

        if (doctor != null) {
            doctor.setActive(true);
            repository.update(doctor);
        } else {
            throw new DoctorNotFoundException(id);
        }
    }


    public int getActiveDoctorCount() throws IOException {
        return getAllActiveDoctors().size();
    }


    public double getDoctorCartTotalPrice(int id) throws IOException, DoctorNotFoundException {
        return getActiveDoctorById(id)
                .getPats()
                .stream()
                .filter(Pat::isActive)
                .mapToDouble(Pat::getPrice)
                .sum();
    }


    public double getDoctorCartAveragePrice(int id) throws IOException, DoctorNotFoundException {
        return getActiveDoctorById(id)
                .getPats()
                .stream()
                .filter(Pat::isActive)
                .mapToDouble(Pat::getPrice)
                .average()
                .orElse(0.0);
    }


    public void addPatToDoctorCart(int doctorId, int patId) throws IOException, DoctorNotFoundException, PatNotFoundException {
       Doctor doctor = getActiveDoctorById(doctorId);
        Pat pat = patService.getActivePatById(patId);
        doctor.getPats().add(pat);
        repository.update(doctor);
    }


    public void removePatFromDoctorCart(int doctorId, int patId) throws IOException, DoctorNotFoundException, PatNotFoundException {
        Doctor doctor = getActiveDoctorById(doctorId);
    Pat pat = patService.getActivePatById(patId);
        doctor.getPats().remove(pat);
        repository.update(doctor);
    }


    public void clearDoctorCart(int id) throws IOException, DoctorNotFoundException {
        Doctor doctor = getActiveDoctorById(id);
        doctor.getPats().clear();
        repository.update(doctor);
    }
}

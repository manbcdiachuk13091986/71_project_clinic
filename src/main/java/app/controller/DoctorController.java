package app.controller;

import app.domain.Doctor;
import app.exceptions.DoctorNotFoundException;
import app.exceptions.DoctorSaveException;
import app.exceptions.DoctorUpdateException;
import app.exceptions.PatNotFoundException;
import app.service.DoctorService;

import java.io.IOException;
import java.util.List;

public class DoctorController {
    private final DoctorService service;

    public DoctorController() throws IOException {
        service = new DoctorService();
    }

    public Doctor save(String name) throws IOException, DoctorSaveException {
       Doctor doctor = new Doctor(name);
        return service.save(doctor);
    }

    public List<Doctor> getAllActiveDoctors() throws IOException {
        return service.getAllActiveDoctors();
    }

    public Doctor getActiveDoctorById(int id) throws IOException, DoctorNotFoundException {
        return service.getActiveDoctorById(id);
    }

    public void update(int id, String name) throws DoctorUpdateException, IOException {
     Doctor doctor = new Doctor(id, name);
        service.update(doctor);
    }

    public void deleteById(int id) throws IOException, DoctorNotFoundException {
        service.deleteById(id);
    }

    public void deleteByName(String name) throws IOException, DoctorNotFoundException {
        service.deleteByName(name);
    }

    public void restoreById(int id) throws IOException, DoctorNotFoundException {
        service.restoreById(id);
    }

    public int getActiveDoctorCount() throws IOException {
        return service.getActiveDoctorCount();
    }

    public double getDoctorCartTotalPrice(int id) throws IOException, DoctorNotFoundException {
        return service.getDoctorCartTotalPrice(id);
    }

    public double getDoctorCartAveragePrice(int id) throws IOException, DoctorNotFoundException {
        return service.getDoctorCartAveragePrice(id);
    }

    public void addPatToDoctorCart(int doctorId, int patId) throws IOException, DoctorNotFoundException, PatNotFoundException {
        service.addPatToDoctorCart(doctorId, patId);
    }

    public void removePatFromDoctorCart(int doctorId, int patId) throws IOException, DoctorNotFoundException, PatNotFoundException {
        service.removePatFromDoctorCart(doctorId, patId);
    }

    public void clearDoctorCart(int id) throws IOException, DoctorNotFoundException {
        service.clearDoctorCart(id);
    }
}

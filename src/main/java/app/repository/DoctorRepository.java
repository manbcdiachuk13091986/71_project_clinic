package app.repository;

import app.domain.Doctor;
import app.domain.Pat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class DoctorRepository {
    private final File database;


    private final ObjectMapper mapper;


    private int maxId;

    public DoctorRepository () throws IOException {
        database = new File("database/doctor.txt");
        mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);


        List<Doctor> doctors = findAll();

        if (!doctors.isEmpty()) {
            Doctor lastDoctor = doctors.get(doctors.size() - 1);
            maxId = lastDoctor.getId();
        }
    }

    public Doctor save(Doctor doctor) throws IOException {
        doctor.setId(++maxId);
        List<Doctor> doctors = findAll();
        doctors.add(doctor);
        mapper.writeValue(database, doctors);
        return doctor;
    }

    public List<Doctor> findAll() throws IOException {
        try {
            Doctor[] doctors = mapper.readValue(database, Doctor[].class);
            return new ArrayList<>(Arrays.asList(doctors));
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    public Doctor findById(int id) throws IOException {
        return findAll()
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void update(Doctor doctor) throws IOException {
        int id = doctor.getId();
        String newName = doctor.getName();
        boolean active = doctor.isActive();
        List<Pat> pats = doctor.getPats();

        List<Doctor> doctors = findAll();
        doctors
                .stream()
                .filter(x -> x.getId() == id)
                .forEach(x -> {
                    x.setName(newName);
                    x.setActive(active);
                    x.setPats(pats);
                });

        mapper.writeValue(database, doctors);
    }

    public void deleteById(int id) throws IOException {
        List<Doctor> doctors = findAll();
        doctors.removeIf(x -> x.getId() == id);
        mapper.writeValue(database, doctors);
    }
}

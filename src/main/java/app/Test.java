package app;

import app.domain.Doctor;
import app.repository.DoctorRepository;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        DoctorRepository repository = new DoctorRepository();

        Doctor doctor1 = new Doctor();
        doctor1.setName("Мария");
        doctor1.setActive(true);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Пётр");
        doctor2.setActive(true);

        Doctor doctor3 = new Doctor();
        doctor3.setName("Владимир");
        doctor3.setActive(true);

        repository.save(doctor1);
        repository.save(doctor2);
        repository.save(doctor3);
    }
}

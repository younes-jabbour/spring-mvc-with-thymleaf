package ma.enset.springdata;

import ma.enset.springdata.entities.Appointment;
import ma.enset.springdata.entities.Medecine;
import ma.enset.springdata.entities.Patient;
import ma.enset.springdata.entities.status;
import ma.enset.springdata.repository.AppointmentRepository;
import ma.enset.springdata.repository.MedecineRepository;
import ma.enset.springdata.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            PatientRepository patientRepository
            , MedecineRepository medecineRepository
                , AppointmentRepository appointmentRepository ) {
        return args -> {
            Stream.of("Hassan", "ali", "salah").forEach(nom -> {
                Medecine medecine = new Medecine();
                medecine.setName(nom);
                medecine.setSpecialite(Math.random() > 0.5 ? "Cardiologist" : "Dentist");
                medecineRepository.save(medecine);
            });

            Stream.of("Younes", "aziz", "ilham").forEach(nom -> {
                Patient patient = new Patient();
                patient.setName(nom);
                patient.setBirthDate(new Date());
                patient.setSick(!(Math.random() > 0.5));
                patientRepository.save(patient);
            });

            Medecine medecine = medecineRepository.findByName("ali");
            Patient patient = patientRepository.findByName("Younes");
            Appointment appointment = new Appointment();


            appointment.setDate(new Date().toString());
            appointment.setTime("10:00");
            appointment.setStatus(status.planifiee);
            appointment.setMedecine(medecine);
            appointment.setPatient(patient);

            appointmentRepository.save(appointment);
        };

    }

}

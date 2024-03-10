package ma.enset.springdata.service;

import ma.enset.springdata.entities.Appointment;
import ma.enset.springdata.entities.Consultation;
import ma.enset.springdata.entities.Medecine;
import ma.enset.springdata.entities.Patient;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.*;

public interface IHospitalService {

    public Medecine registerMedecine(Medecine m);

    public void deletePatient(Long id);

    public void deleteMedecine(Long id);

    public Patient registerPatient(Patient patient);

    List<Patient> getPatients();

    public List<Medecine> getMedicines();

    public Appointment registerAppointment(Appointment a);

    public Consultation registerConsultation(Consultation c);

    public List<Medecine> searchMedecine(String name);

    Page<Patient> searchPatient(String keyword, Pageable pageable);
}

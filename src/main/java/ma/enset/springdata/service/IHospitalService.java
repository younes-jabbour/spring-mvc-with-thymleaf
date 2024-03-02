package ma.enset.springdata.service;

import ma.enset.springdata.entities.Appointment;
import ma.enset.springdata.entities.Consultation;
import ma.enset.springdata.entities.Medecine;
import ma.enset.springdata.entities.Patient;

import java.util.List;

public interface IHospitalService {

    public Medecine registerMedecine(Medecine m);

    public Patient registerPatient(Patient p);

    public Appointment registerAppointment(Appointment a);

    public Consultation registerConsultation(Consultation c);

    public List<Medecine> searchMedecine(String name);

    public List<Patient> searchPatient(String name);

}

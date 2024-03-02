package ma.enset.springdata.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.springdata.entities.Appointment;
import ma.enset.springdata.entities.Consultation;
import ma.enset.springdata.entities.Medecine;
import ma.enset.springdata.entities.Patient;
import ma.enset.springdata.repository.AppointmentRepository;
import ma.enset.springdata.repository.ConsultationRepository;
import ma.enset.springdata.repository.MedecineRepository;
import ma.enset.springdata.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    private MedecineRepository medecineRepository;
    private AppointmentRepository appointmentRepository;


    @Override
    public Medecine registerMedecine(Medecine m) {
   return  medecineRepository.save(m);
    }

    @Override
    public Patient registerPatient(Patient p) {
        return  patientRepository.save(p);
    }

    @Override
    public Appointment registerAppointment(Appointment a) {
    return appointmentRepository.save(a);
    }

    @Override
    public Consultation registerConsultation(Consultation c) {
        return  consultationRepository.save(c);
    }

    @Override
    public List<Medecine> searchMedecine(String name) {
        return medecineRepository.findByName(name);
    }

    @Override
    public List<Patient> searchPatient(String name) {
        return patientRepository.findByName(name);
    }
}

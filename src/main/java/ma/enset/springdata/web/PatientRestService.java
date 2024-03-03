package ma.enset.springdata.web;

import ma.enset.springdata.entities.Patient;
import ma.enset.springdata.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PatientRestService {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientRepository.getAll();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}

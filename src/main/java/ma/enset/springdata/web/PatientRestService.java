package ma.enset.springdata.web;

import ma.enset.springdata.entities.Patient;
import ma.enset.springdata.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PatientRestService {

    @Autowired
    private PatientRepository patientRepository;

//    @GetMapping("/patient")
//    public List<Patient> getPatients() {
//        return patientRepository.getAll();
//    }

    @PostMapping("/createPatient")
    public String registerPatient(@RequestBody Patient patient) {
        return "Patient created successfully";
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}

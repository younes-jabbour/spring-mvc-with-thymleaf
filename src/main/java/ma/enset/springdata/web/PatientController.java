package ma.enset.springdata.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.springdata.entities.Patient;
import ma.enset.springdata.repository.PatientRepository;
import ma.enset.springdata.service.IHospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Controller
public class PatientController {

    private final IHospitalService iHospitalService;
    private final PatientRepository patientRepository;


    @GetMapping("/user/index")
    public String patients(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Patient> pagePatients = iHospitalService.searchPatient(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "patients";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/admin/deletePatient")
    public String deletePatient(Model model, @RequestParam(name = "id") Long id, @RequestParam(name = "page") int page, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        iHospitalService.deletePatient(id);
        model.addAttribute("keyword", keyword);
        return "redirect:/user/index?page=" + page;
    }

    @PostMapping("/admin/updatePatient")
    public String updatePatient(@RequestParam Long id, @RequestParam String name, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate, @RequestParam Boolean sick, @RequestParam int score) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        patient.setName(name);
        patient.setBirthDate(birthdate);
        patient.setSick(sick);
        patient.setScore(score);
        patientRepository.updatePatient(patient);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @PostMapping("/admin/addPatient")
    public String addPatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "add-patient";
        Patient p = iHospitalService.registerPatient(patient);
        return "redirect:/user/index";
    }
}

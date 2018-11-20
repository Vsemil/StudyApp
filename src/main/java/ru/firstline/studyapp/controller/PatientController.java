package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.firstline.studyapp.model.dto.Patient;
import ru.firstline.studyapp.service.PatientService;

@RestController
@RequestMapping(value = "patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/all")
    public Page<Patient> getAll(Pageable pageable) {
        return patientService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public Patient getById(@PathVariable Integer id) {
        return patientService.findById(id);
    }

    @PostMapping(value = "/save")
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        patientService.delete(id);
    }
}

package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.firstline.studyapp.model.dto.Patient;
import ru.firstline.studyapp.service.PatientService;

import java.util.List;

@RestController
@RequestMapping(value = "patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/all")
    public Page<Patient> getAll(Pageable pageable) {
        return patientService.findAll(pageable);
    }

    @GetMapping(value = "/allList")
    public List<Patient> getAllList() {
        return patientService.findAll();
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

    @PostMapping(value = "/addFiles")
    public void addFiles(@RequestParam("files") MultipartFile[] uploadfiles,
                         @RequestParam("patientId") Integer patientId) {
        patientService.addFiles(uploadfiles, patientId);
    }

    @GetMapping(value = "/getFile/{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable String id) {
        return patientService.getFile(id);
    }

    @DeleteMapping(value = "/deleteFile/{id}")
    public void deleteFile(@PathVariable String id){
        patientService.deleteFile(id);
    }
}

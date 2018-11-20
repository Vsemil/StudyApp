package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.firstline.studyapp.model.dto.Doctor;
import ru.firstline.studyapp.service.DoctorService;

@RestController
@RequestMapping(value = "doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/all")
    public Page<Doctor> getAll(Pageable pageable) {
        return doctorService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public Doctor getById(@PathVariable Integer id) {
        return doctorService.findById(id);
    }

    @PostMapping(value = "/save")
    public Doctor save(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        doctorService.delete(id);
    }
}

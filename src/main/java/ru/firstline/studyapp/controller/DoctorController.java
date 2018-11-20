package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

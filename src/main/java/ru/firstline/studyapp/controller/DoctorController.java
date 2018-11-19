package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.firstline.studyapp.model.DoctorEntity;
import ru.firstline.studyapp.repository.DoctorRepository;

import java.util.List;

@RestController
@RequestMapping(value = "doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping(value = "/all")
    public List<DoctorEntity> getAll() {
        return doctorRepository.findAll();
    }
}

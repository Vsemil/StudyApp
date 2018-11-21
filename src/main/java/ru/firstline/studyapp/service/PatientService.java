package ru.firstline.studyapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.firstline.studyapp.model.dto.Patient;

import java.util.List;

public interface PatientService {
    Page<Patient> findAll(Pageable pageable);

    List<Patient> findAll();

    Patient findById(Integer id);

    Patient save(Patient patient);

    void delete(Patient patient);

    void delete(Integer id);
}

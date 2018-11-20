package ru.firstline.studyapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.firstline.studyapp.model.dto.Doctor;

public interface DoctorService {
    Page<Doctor> findAll(Pageable pageable);

    Doctor findById(Integer id);

    Doctor save(Doctor doctor);

    void delete(Doctor doctor);

    void delete(Integer id);
}

package ru.firstline.studyapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.firstline.studyapp.exception.NotFoundException;
import ru.firstline.studyapp.model.dto.Doctor;

public interface DoctorService {
    Page<Doctor> findAll(Pageable pageable);

    Doctor findById(Integer id) throws NotFoundException;

    Doctor save(Doctor doctor);

    void delete(Doctor doctor);

    void delete(Integer id);
}

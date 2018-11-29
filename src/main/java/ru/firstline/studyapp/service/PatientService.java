package ru.firstline.studyapp.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.firstline.studyapp.model.dto.Patient;

import java.util.List;

public interface PatientService {
    Page<Patient> findAll(Pageable pageable);

    List<Patient> findAll();

    Patient findById(Integer id);

    Patient save(Patient patient);

    void delete(Patient patient);

    void delete(Integer id);

    void addFiles(MultipartFile[] uploadfiles, Integer patientId);

    ResponseEntity<InputStreamResource> getFile(String id);

    void deleteFile(String id);
}

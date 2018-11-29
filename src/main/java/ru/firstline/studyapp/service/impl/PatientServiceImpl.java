package ru.firstline.studyapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.firstline.studyapp.exception.NotFoundException;
import ru.firstline.studyapp.model.DBFile;
import ru.firstline.studyapp.model.PatientEntity;
import ru.firstline.studyapp.model.dto.Patient;
import ru.firstline.studyapp.model.mapper.PatientMapper;
import ru.firstline.studyapp.repository.DBFileRepository;
import ru.firstline.studyapp.repository.PatientRepository;
import ru.firstline.studyapp.service.PatientService;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private static String UPLOADED_FOLDER = "/home/ivan/temp/";

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DBFileRepository dbFileRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Page<Patient> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable).map(pe -> patientMapper.map(pe, Patient.class));
    }

    @Override
    public List<Patient> findAll() {
        return patientMapper.mapAsList(patientRepository.findAll(), Patient.class);
    }

    @Override
    public Patient findById(Integer id) {
        return patientMapper.map(patientRepository.findById(id).orElseThrow(()->new NotFoundException("Patient not found")), Patient.class);
    }

    @Override
    public Patient save(Patient patient) {
        if (patient != null) {
            PatientEntity patientEntity = patientMapper.map(patient, PatientEntity.class);
            return patientMapper.map(patientRepository.save(patientEntity), Patient.class);
        }
        return null;
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.delete(patientMapper.map(patient, PatientEntity.class));
    }

    @Override
    public void delete(Integer id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void addFiles(MultipartFile[] uploadfiles, Integer patientId) {
        PatientEntity patient = patientRepository.findById(patientId).orElseThrow(()->new NotFoundException("Patient not found"));
        try {
            if (!Files.exists(Paths.get(UPLOADED_FOLDER))) {
                Files.createDirectories(Paths.get(UPLOADED_FOLDER));
            }

            for (MultipartFile file : uploadfiles) {

                if (file.isEmpty()) {
                    continue;
                }
                String uuid = UUID.randomUUID().toString();
                Path path = Paths.get(UPLOADED_FOLDER + uuid + "." + file.getOriginalFilename());
                Files.write(path, file.getBytes());
                DBFile dbFile = new DBFile(file.getOriginalFilename(), file.getContentType(),
                        path.toAbsolutePath().toString(), patient);
                patient.getFiles().add(dbFile);
            }
        } catch (IOException ex) {
            throw new RuntimeException("IOError upload files");
        }
        patientRepository.save(patient);
    }

    @Override
    public ResponseEntity<InputStreamResource> getFile(String id) {
        DBFile dbFile = dbFileRepository.findById(id).orElseThrow(()->new NotFoundException("File not found"));
        Path path = Paths.get(dbFile.getPath());
        if (!Files.exists(path)) {
            throw new NotFoundException("File not found");
        }
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + dbFile.getFileName())
                    .contentType(MediaType.parseMediaType(dbFile.getFileType())).contentLength(path.toFile().length())
                    .body(resource);
        }catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @Override
    public void deleteFile(String id) {
        DBFile dbFile = dbFileRepository.findById(id).orElseThrow(()->new NotFoundException("File not found"));
        dbFile.getPatient().getFiles().remove(dbFile);
        patientRepository.save(dbFile.getPatient());
    }
}

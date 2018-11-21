package ru.firstline.studyapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.firstline.studyapp.exception.NotFoundException;
import ru.firstline.studyapp.model.PatientEntity;
import ru.firstline.studyapp.model.dto.Patient;
import ru.firstline.studyapp.model.mapper.PatientMapper;
import ru.firstline.studyapp.repository.PatientRepository;
import ru.firstline.studyapp.service.PatientService;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

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
}

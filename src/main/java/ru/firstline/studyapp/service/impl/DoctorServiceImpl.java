package ru.firstline.studyapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.firstline.studyapp.exception.NotFoundException;
import ru.firstline.studyapp.model.DoctorEntity;
import ru.firstline.studyapp.model.dto.Doctor;
import ru.firstline.studyapp.model.mapper.DoctorMapper;
import ru.firstline.studyapp.repository.DoctorRepository;
import ru.firstline.studyapp.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Page<Doctor> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable).map(doctorEntity -> doctorMapper.map(doctorEntity, Doctor.class));
    }

    @Override
    public Doctor findById(Integer id) throws NotFoundException {
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(()->new NotFoundException("Doctor not found!"));
        return doctorMapper.map(doctorEntity, Doctor.class);
    }

    @Override
    public Doctor save(Doctor doctor) {
        if (doctor != null) {
            DoctorEntity doctorEntity = doctorMapper.map(doctor, DoctorEntity.class);
            return doctorMapper.map(doctorRepository.save(doctorEntity), Doctor.class);
        }
        return null;
    }

    @Override
    public void delete(Doctor doctor) {
        doctorRepository.delete(doctorMapper.map(doctor, DoctorEntity.class));
    }

    @Override
    public void delete(Integer id) {
        doctorRepository.deleteById(id);
    }
}

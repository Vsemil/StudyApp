package ru.firstline.studyapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.firstline.studyapp.model.StudyEntity;
import ru.firstline.studyapp.model.dto.Study;
import ru.firstline.studyapp.model.mapper.StudyMapper;
import ru.firstline.studyapp.repository.StudyRepository;
import ru.firstline.studyapp.service.StudyService;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public Page<Study> findAll(Pageable pageable) {
        return studyRepository.findAll(pageable).map(se -> studyMapper.map(se, Study.class));
    }

    @Override
    public Study findById(Integer id) {
        return studyMapper.map(studyRepository.findById(id), Study.class);
    }

    @Override
    public Study save(Study study) {
        if (study != null) {
            StudyEntity studyEntity = studyMapper.map(study, StudyEntity.class);
            return studyMapper.map(studyRepository.save(studyEntity), Study.class);
        }
        return null;
    }

    @Override
    public void delete(Study study) {
        studyRepository.delete(studyMapper.map(study, StudyEntity.class));
    }

    @Override
    public void delete(Integer id) {
        studyRepository.deleteById(id);
    }
}

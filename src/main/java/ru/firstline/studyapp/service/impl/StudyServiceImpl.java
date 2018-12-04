package ru.firstline.studyapp.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import ru.firstline.studyapp.exception.NotFoundException;
import ru.firstline.studyapp.model.StudyEntity;
import ru.firstline.studyapp.model.dto.Study;
import ru.firstline.studyapp.model.mapper.StudyMapper;
import ru.firstline.studyapp.repository.StudyRepository;
import ru.firstline.studyapp.repository.elasticsearch.StudyElasticRepository;
import ru.firstline.studyapp.service.StudyService;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyElasticRepository studyElasticRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public Page<Study> findAll(Pageable pageable) {
        return studyRepository.findAll(pageable).map(se -> studyMapper.map(se, Study.class));
    }

    @Override
    public Study findById(String id) {
        return studyMapper.map(studyRepository.findById(id).orElseThrow(()->new NotFoundException("Study not found")), Study.class);
    }

    @Override
    public Study save(Study study) {
        if (study != null) {
            StudyEntity studyEntity = studyMapper.map(study, StudyEntity.class);
            studyElasticRepository.save(studyEntity);
            return studyMapper.map(studyRepository.save(studyEntity), Study.class);
        }
        return null;
    }

    @Override
    public void delete(Study study) {
        StudyEntity studyEntity = studyMapper.map(study, StudyEntity.class);
        studyRepository.delete(studyEntity);
        studyElasticRepository.delete(studyEntity);
    }

    @Override
    public void delete(String id) {
        StudyEntity studyEntity = studyRepository.findById(id).orElseThrow(()->new NotFoundException("Study not found"));
        studyRepository.delete(studyEntity);
        studyElasticRepository.delete(studyEntity);
    }

    @Override
    public void setStatus(Study study) {
        StudyEntity studyEntity = studyRepository.findById(study.getId())
                .orElseThrow(()->new NotFoundException("Study not found"));
        studyEntity.setStatus(study.getStatus());
        studyRepository.save(studyEntity);
    }

    @Override
    public Page<Study> findAll(Pageable pageable, String searchWord) {
//        studyElasticRepository.deleteAll();
//        elasticsearchTemplate.deleteIndex(StudyEntity.class);
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery(searchWord)
                        .lenient(true)
                        .field("description")
                        .field("status")
                        .field("patient.name"))
                .should(QueryBuilders.queryStringQuery("*" + searchWord + "*")
                        .lenient(true)
                        .field("description")
                        .field("status")
                        .field("patient.name"));

        Page<StudyEntity> studyEntity = studyElasticRepository.search(query, pageable);
//        Page<StudyEntity> studyEntity = studyElasticRepository.findAllByDescription(searchWord, pageable);
        return studyEntity.map(se -> studyMapper.map(se, Study.class));
    }
}

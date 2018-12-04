package ru.firstline.studyapp.repository.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.firstline.studyapp.model.StudyEntity;

public interface StudyElasticRepository extends ElasticsearchRepository<StudyEntity, String> {
    Page<StudyEntity> findAllByDescription(String searchWord, Pageable pageable);
}

package ru.firstline.studyapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.firstline.studyapp.model.dto.Study;

public interface StudyService {
    Page<Study> findAll(Pageable pageable);

    Study findById(String id);

    Study save(Study study);

    void delete(Study study);

    void delete(String id);

    void setStatus(Study study);

    Page<Study> findAll(Pageable pageable, String searchWord);
}

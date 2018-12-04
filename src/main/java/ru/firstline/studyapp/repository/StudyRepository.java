package ru.firstline.studyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.firstline.studyapp.model.StudyEntity;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, String> {
}

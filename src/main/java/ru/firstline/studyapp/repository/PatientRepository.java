package ru.firstline.studyapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.firstline.studyapp.model.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    @Override
//    @Query(value = "select p from PatientEntity p join fetch p.files f")
    @EntityGraph(value = "allFiles" , type=EntityGraph.EntityGraphType.FETCH)
    Page<PatientEntity> findAll(Pageable pageable);
}

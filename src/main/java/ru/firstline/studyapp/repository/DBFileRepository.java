package ru.firstline.studyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.firstline.studyapp.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
}

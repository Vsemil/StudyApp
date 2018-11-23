package ru.firstline.studyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.firstline.studyapp.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}

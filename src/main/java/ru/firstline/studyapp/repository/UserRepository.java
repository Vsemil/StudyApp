package ru.firstline.studyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.firstline.studyapp.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}

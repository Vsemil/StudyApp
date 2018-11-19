package ru.firstline.studyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.firstline.studyapp.model.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
}

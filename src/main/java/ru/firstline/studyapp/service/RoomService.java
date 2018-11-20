package ru.firstline.studyapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.firstline.studyapp.model.dto.Room;

public interface RoomService {
    Page<Room> findAll(Pageable pageable);

    Room findById(Integer id);

    Room save(Room room);

    void delete(Room room);

    void delete(Integer id);
}

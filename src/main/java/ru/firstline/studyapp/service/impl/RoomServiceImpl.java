package ru.firstline.studyapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.firstline.studyapp.model.RoomEntity;
import ru.firstline.studyapp.model.dto.Room;
import ru.firstline.studyapp.model.mapper.RoomMapper;
import ru.firstline.studyapp.repository.RoomRepository;
import ru.firstline.studyapp.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable).map(re -> roomMapper.map(re, Room.class));
    }

    @Override
    public Room findById(Integer id) {
        return roomMapper.map(roomRepository.findById(id), Room.class);
    }

    @Override
    public Room save(Room room) {
        if (room != null) {
            RoomEntity roomEntity = roomMapper.map(room, RoomEntity.class);
            return roomMapper.map(roomRepository.save(roomEntity), Room.class);
        }
        return null;
    }

    @Override
    public void delete(Room room) {
        if (room != null) {
            roomRepository.delete(roomMapper.map(room, RoomEntity.class));
        }
    }

    @Override
    public void delete(Integer id) {
        roomRepository.deleteById(id);
    }
}

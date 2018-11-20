package ru.firstline.studyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.firstline.studyapp.model.dto.Room;
import ru.firstline.studyapp.service.RoomService;

@RestController
@RequestMapping(value = "rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/all")
    public Page<Room> getAll(Pageable pageable) {
        return roomService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public Room getById(@PathVariable Integer id) {
        return roomService.findById(id);
    }

    @PostMapping(value = "/save")
    public Room save(@RequestBody Room room) {
        return roomService.save(room);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        roomService.delete(id);
    }
}

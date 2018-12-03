package ru.firstline.studyapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.firstline.studyapp.model.UserEntity;

@RestController
public class UserController {

    @GetMapping("/user")
    public UserEntity getUser(@AuthenticationPrincipal UserEntity user) {
        return user;
    }
}

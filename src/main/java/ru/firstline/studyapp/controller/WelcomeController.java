package ru.firstline.studyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/123")
    public String index() {
        System.out.println("----This line is executed on tomcat console---");
        return "index";
    }
}

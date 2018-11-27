package ru.firstline.studyapp;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityJavaConfigTest {
    @Test
    public void encoder() {
        System.out.println(new BCryptPasswordEncoder().encode("2"));
    }

}

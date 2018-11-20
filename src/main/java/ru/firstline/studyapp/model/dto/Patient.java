package ru.firstline.studyapp.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Boolean sex;
    private LocalDate dayOfBirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}

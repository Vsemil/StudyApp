package ru.firstline.studyapp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Boolean sex;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dayOfBirth;
    private List<FileDTO> files;

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> fileIds) {
        this.files = fileIds;
    }

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

package ru.firstline.studyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
public class PatientEntity extends AbstractNamedEntity{

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "day_of_birth")
    @JsonIgnore
    private LocalDate dayOfBirth;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<DBFile> files;

    public PatientEntity() {
    }

    public PatientEntity(Integer id, String name, Boolean sex, LocalDate dayOfBirth) {
        super(id, name);
        this.sex = sex;
        this.dayOfBirth = dayOfBirth;
    }

    public List<DBFile> getFiles() {
        return files;
    }

    public void setFiles(List<DBFile> files) {
        this.files = files;
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

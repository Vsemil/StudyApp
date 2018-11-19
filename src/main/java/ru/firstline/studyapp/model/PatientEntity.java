package ru.firstline.studyapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class PatientEntity extends AbstractNamedEntity{

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "day_of_birth")
    private LocalDate dayOfBirth;

    public PatientEntity() {
    }

    public PatientEntity(Integer id, String name, Boolean sex, LocalDate dayOfBirth) {
        super(id, name);
        this.sex = sex;
        this.dayOfBirth = dayOfBirth;
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

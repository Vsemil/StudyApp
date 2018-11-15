package ru.firstline.studyapp.model;

import java.time.LocalDate;

public class PatientEntity extends AbstractNamedEntity{
    private Boolean sex;
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

package ru.firstline.studyapp.model;

import java.time.LocalDateTime;

public class StudyEntity extends AbstractBaseEntity {
    private PatientEntity patient;
    private String description;
    private Status status;
    private LocalDateTime plannedStartTime;
    private LocalDateTime estimatedEndTime;

    public StudyEntity() {
    }

    public StudyEntity(Integer id, PatientEntity patient, String description, Status status, LocalDateTime plannedStartTime, LocalDateTime estimatedEndTime) {
        super(id);
        this.patient = patient;
        this.description = description;
        this.status = status;
        this.plannedStartTime = plannedStartTime;
        this.estimatedEndTime = estimatedEndTime;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(LocalDateTime plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public LocalDateTime getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(LocalDateTime estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }
}

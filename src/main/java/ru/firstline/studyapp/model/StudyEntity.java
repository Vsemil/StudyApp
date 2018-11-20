package ru.firstline.studyapp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "study")
public class StudyEntity extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    @NonNull
    private PatientEntity patient;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    @NonNull
    private Status status;

    @Column(name = "planned_start_time", nullable = false)
    @NonNull
    private LocalDateTime plannedStartTime;

    @Column(name = "estimated_end_time")
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

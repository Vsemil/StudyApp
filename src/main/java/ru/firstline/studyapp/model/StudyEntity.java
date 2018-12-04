package ru.firstline.studyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "study")
@Document(indexName = "studyapp", type = "study")
public class StudyEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    protected String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    @NonNull
    @Field(type = FieldType.Nested, includeInParent = true)
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
    @JsonIgnore
    private LocalDateTime plannedStartTime;

    @Column(name = "estimated_end_time")
    @JsonIgnore
    private LocalDateTime estimatedEndTime;

    public StudyEntity() {
    }

    public StudyEntity(String id, PatientEntity patient, String description, Status status, LocalDateTime plannedStartTime, LocalDateTime estimatedEndTime) {
        this.id = id;
        this.patient = patient;
        this.description = description;
        this.status = status;
        this.plannedStartTime = plannedStartTime;
        this.estimatedEndTime = estimatedEndTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

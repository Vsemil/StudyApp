package ru.firstline.studyapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class DoctorEntity extends AbstractNamedEntity {
}

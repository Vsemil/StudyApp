package ru.firstline.studyapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class RoomEntity extends AbstractNamedEntity {
}

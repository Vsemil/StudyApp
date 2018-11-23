package ru.firstline.studyapp.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends AbstractNamedEntity implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return this.name;
    }
}

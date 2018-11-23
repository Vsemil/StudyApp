package ru.firstline.studyapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usr")
public class UserEntity extends AbstractBaseEntity{

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany()
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}

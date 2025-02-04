package com.azathoth.SimpleCRUD.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // automatic generate time when the user is created
    @CreationTimestamp
    @Column(name = "user_date_in", updatable = false)
    private Date dateIn;

    @Column(name = "complete_name",
        length = 255, nullable = false
    ) private String completeName;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    public UserModel(String completeName, String username, String password) {
        this.completeName = completeName;
        this.username = username;
        this.password = password;
    }

    public UserModel() {}

    public String getCompleteName() {
        return completeName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

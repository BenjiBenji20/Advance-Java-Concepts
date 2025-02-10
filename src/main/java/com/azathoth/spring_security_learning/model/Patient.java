package com.azathoth.spring_security_learning.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_username", length = 255, nullable = false)
    private String username;

    @Column(name = "patient_password", length = 255, nullable = false)
    private String password;

    @Column(name = "patient_age", length = 3, nullable = false)
    private int age;

    @Column(name = "user_role", nullable = false)
    private String role;

    public Patient(int id, String username, String password, int age, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public Patient(){}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientModel{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

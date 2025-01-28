package com.demo.hql;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor_catmon_center")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "doctor_name",
            length = 255,
            nullable = false)
    private String name;

    @Column(name = "doctor_gender", nullable = false)
    private String gender;

    @OneToMany(mappedBy = "doctor",
        cascade = CascadeType.ALL
    )
    @Column(name = "doctor_patients",
            length = 255
    )
    private List<Patient> patients = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", patients=" + (patients != null ? patients : "") + // Avoid printing entire patients list
                '}';
    }
}

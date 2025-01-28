package com.demo.hql;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "patient_catmor_center")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "patient_date_in", updatable = false)
    private Date dateIn;

    @Column(name = "patient_name",
            length = 255,
            nullable = false
    )
    private String name;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public int getId() {
        return id;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateIn=" + dateIn +
                ", doctorId=" + (doctor != null ? doctor.getId() : null) + // Avoid printing entire doctor object
                '}';
    }
}

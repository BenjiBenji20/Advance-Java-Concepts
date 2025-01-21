package com.demo.mapping.relationship;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate an auto-incremented primary-key column
    private int studentId;

    @Column(name = "Kumpletong Pangalan")
    private StudentName name;

    @OneToOne
    private Laptop laptop;

    public StudentName getName() {
        return name;
    }

    public void setName(StudentName name) {
        this.name = name;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", name=" + name +
                ", laptop=" + laptop +
                '}';
    }
}

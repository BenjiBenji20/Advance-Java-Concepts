package com.demo.mapping.relationship;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate an auto-incremented primary-key column
    private int Id;

    @Column(name = "Kumpletong Pangalan")
    private StudentName name;

    @OneToMany(mappedBy = "student")
    private List<Laptop> laptops = new ArrayList<>();

    public StudentName getName() {
        return name;
    }

    public void setName(StudentName name) {
        this.name = name;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }
}

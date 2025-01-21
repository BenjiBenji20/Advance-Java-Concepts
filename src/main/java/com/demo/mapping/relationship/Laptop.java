package com.demo.mapping.relationship;

import jakarta.persistence.*;

@Entity
@Table(name = "Laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate an auto-incremented primary-key column
    private int laptopId;

    private String brand;
    private String unit;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + laptopId +
                ", brand='" + brand + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}

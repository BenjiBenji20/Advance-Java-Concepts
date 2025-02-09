package com.azathoth.spring_security_learning.model;

import org.springframework.stereotype.Component;

@Component
public class PatientModel {
    private int id;
    private String name;
    private int age;

    public PatientModel(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public PatientModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

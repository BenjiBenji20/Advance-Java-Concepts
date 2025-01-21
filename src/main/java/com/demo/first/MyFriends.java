package com.demo.first;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MgaKaibigan")
public class MyFriends {

    @Id
    private int id;

    @Column(name = "edad")
    private int age;

    @Column(name = "kasarian")
    private String gender;

    @Column(name = "pangalan")
    private FriendsName name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public FriendsName getName() {
        return name;
    }

    public void setName(FriendsName name) {
        this.name = name;
    }

    // for fetching data using session.get();
    @Override
    public String toString() {
        return "MyFriends{" +
                "id=" + id +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", name=" + name +
                '}';
    }
}

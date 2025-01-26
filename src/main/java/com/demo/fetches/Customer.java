package com.demo.fetches;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name",
        nullable = false,
        length = 255
    )
    private String customerName;

    @OneToMany(mappedBy = "customer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Order> orders = new ArrayList<>();

    public int getId() {
        return Id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "Id=" + Id +
                ", customerName='" + customerName + '\'' +
                ", orders=" + orders +
                '}';
    }
}

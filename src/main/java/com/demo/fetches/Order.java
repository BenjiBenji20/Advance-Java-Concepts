package com.demo.fetches;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "customer_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // creates auto generated date and time
    @Column(name = "order_date", updatable = false)
    private Date orderDate;

    @Column(name = "product_name",
        length = 255,
        nullable = false
    )
    private String productName;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public int getId() {
        return Id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Customer getCostumer() {
        return customer;
    }

    public void setCostumer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", orderDate=" + orderDate +
                ", productName='" + productName + '\'' +
                ", customer=" + customer +
                '}';
    }
}

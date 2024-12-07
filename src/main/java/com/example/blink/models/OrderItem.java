package com.example.blink.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

import java.util.List;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private int quantity;
    private double amount;

    @ManyToMany
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false) // Ensure foreign key column is correctly defined
    private Orders orders;


    public OrderItem(Long orderItemId, int quantity, double amount, List<Product> product, Orders orders) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.amount = amount;
        this.product = product;
        this.orders = orders;
    }



    public OrderItem() {
        super();
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}

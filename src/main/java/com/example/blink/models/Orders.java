package com.example.blink.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Date date;
    private LocalTime time;
    private double totalAmount;
    private Date deliveryDate;
    private String status;
    private String deliverAddress;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;


    @OneToOne
    private Payment payment;

    public Orders(Long orderId, Date date, LocalTime time, double totalAmount, Date deliveryDate, String status, String deliverAddress, User user, List<OrderItem> orderItems, Payment payment) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.totalAmount = totalAmount;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.deliverAddress = deliverAddress;
        this.user = user;
        this.orderItems = orderItems;
        this.payment = payment;
    }
    public Orders() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

package com.example.blink.dto;

import com.example.blink.models.OrderItem;
import com.example.blink.models.User;

import java.util.List;

public class OrderRequest {
    private User user;
    private String payment;
    private String deliveryAddress;
    private List<OrderItem> items;

    public OrderRequest() {
        super();
    }

    public OrderRequest(User user, String payment, String deliveryAddress, List<OrderItem> items) {
        this.user = user;
        this.payment = payment;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}

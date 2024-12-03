package com.example.blink.dto;

import java.util.Date;

public class CartItemRequest {
    private int quantity;
    private double totalPrice;
    private String status;
    private String productId;

    public CartItemRequest(int quantity, double totalPrice, String status, String productId) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.productId = productId;
    }

    public CartItemRequest() {
        super();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

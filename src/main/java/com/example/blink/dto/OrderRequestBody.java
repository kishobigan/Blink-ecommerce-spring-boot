package com.example.blink.dto;
import java.util.List;
import java.util.Map;

public class OrderRequestBody {
    private String paymentType;
    private Map<String, Integer> items;
    private String deliveryAddress;

    public OrderRequestBody(String paymentType, Map<String, Integer> items, String deliveryAddress) {
        this.paymentType = paymentType;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderRequestBody() {
        super();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }
}

package com.example.blink.services;

import com.example.blink.models.OrderItem;
import com.example.blink.repositories.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    OrderItemsRepository orderItemsRepository;

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemsRepository.save(orderItem);
    }
}

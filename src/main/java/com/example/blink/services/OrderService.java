package com.example.blink.services;

import com.example.blink.dto.OrderRequest;
import com.example.blink.models.*;
import com.example.blink.repositories.OrderItemsRepository;
import com.example.blink.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public Orders createOrder(OrderRequest orderRequest) {
        LocalDate localDeliveryDate = LocalDate.now().plusDays(3);
        Date deliveryDate = Date.from(localDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        double totalAmount = 0;
        List<OrderItem> orderItems = orderRequest.getItems();

        // Calculate total amount
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                totalAmount += item.getAmount();
            }
        }

        // Create Orders object
        Orders orders = new Orders();
        orders.setDate(new Date());
        orders.setTime(LocalTime.now());
        orders.setUser(orderRequest.getUser());
        orders.setStatus("ON PROCESSING");
        orders.setDeliveryDate(deliveryDate);
        orders.setDeliverAddress(orderRequest.getDeliveryAddress());
        orders.setTotalAmount(totalAmount);

        // Handle OrderItems
        List<OrderItem> managedOrderItems = new ArrayList<>();
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                // Detach the entity from persistence context and create a new one if necessary
                OrderItem managedItem;
                if (item.getOrderItemId() != null) { // If it has an ID, assume it's already in DB
                    managedItem = orderItemsRepository.findById(item.getOrderItemId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid OrderItem ID: " + item.getOrderItemId()));
                } else {
                    managedItem = new OrderItem();
                    managedItem.setQuantity(item.getQuantity());
                    managedItem.setAmount(item.getAmount());
                    managedItem.setProduct(item.getProduct());
                }
                managedItem.setOrders(orders); // Associate with Orders
                managedOrderItems.add(managedItem);
            }
        }
        orders.setOrderItems(managedOrderItems);

        // Save Orders and cascade persist OrderItems
        return orderRepository.save(orders);
    }


    public Orders findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}

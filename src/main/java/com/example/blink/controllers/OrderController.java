package com.example.blink.controllers;


import com.example.blink.dto.OrderRequest;
import com.example.blink.dto.OrderRequestBody;
import com.example.blink.models.OrderItem;
import com.example.blink.models.Orders;
import com.example.blink.models.Product;
import com.example.blink.models.User;
import com.example.blink.services.OrderService;
import com.example.blink.services.ProductService;
import com.example.blink.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody OrderRequestBody order) {
        String userId = request.getAttribute("userId").toString();
        System.out.println("userId = " + userId);
        User user = userService.getUserByUserId(userId);

        List<OrderItem> orderItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : order.getItems().entrySet()) {
            OrderItem orderItem = new OrderItem();
            Long itemId =Long.parseLong(entry.getKey());
            int quantity = entry.getValue();
            Product product = productService.getProductById(itemId);
            orderItem.setQuantity(quantity);
            orderItem.setAmount(product.getProductPrice()*itemId);
            orderItems.add(orderItem);
        }

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUser(user);
        orderRequest.setItems(orderItems);
        orderRequest.setPayment(order.getPaymentType());
        orderRequest.setDeliveryAddress(order.getDeliveryAddress());


        try {
            Orders orders = orderService.createOrder(orderRequest);
            return new ResponseEntity<>(orders, HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}

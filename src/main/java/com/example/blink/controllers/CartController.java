package com.example.blink.controllers;

import com.example.blink.models.Cart;
import com.example.blink.services.CartService;
import com.example.blink.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @GetMapping("/create-cart")
    public ResponseEntity<?> createCart(HttpServletRequest request) {
        String userId = request.getAttribute("userId").toString();
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/get-cart")
    public ResponseEntity<?> getCart(HttpServletRequest request) {
        String userId = request.getAttribute("userId").toString();
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }
}

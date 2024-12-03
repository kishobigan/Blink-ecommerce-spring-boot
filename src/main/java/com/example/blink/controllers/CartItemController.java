package com.example.blink.controllers;

import com.example.blink.dto.CartItemRequest;
import com.example.blink.models.CartItem;
import com.example.blink.services.CartItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add-cart-item")
    public ResponseEntity<?> addCartItem(HttpServletRequest request, @RequestBody CartItemRequest cartItemRequest) {
        try {
            String userId = request.getAttribute("userId").toString();
            cartItemService.createCartItem(userId, cartItemRequest);
            return ResponseEntity.ok("Product Successfully add to cart");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/remove-cart-item")
    public ResponseEntity<?> removeCartItem(HttpServletRequest request, @RequestBody CartItemRequest cartItemRequest) {
        try {
            String userId = request.getAttribute("userId").toString();
            cartItemService.removeCartItem(userId, cartItemRequest);
            return ResponseEntity.ok("Successfully item removed from cart");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

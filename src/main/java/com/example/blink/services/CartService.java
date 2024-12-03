package com.example.blink.services;

import com.example.blink.models.Cart;
import com.example.blink.models.CartItem;
import com.example.blink.models.User;
import com.example.blink.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    public Cart createCart(String userId) {
        try {
            System.out.println("userId = " + userId);
            User user = userService.getUserByUserId(userId);
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepository.save(cart);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Cart getCart(String userId) {
        try{
            Cart cart = cartRepository.findByUser(userService.getUserByUserId(userId));
            return cart;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

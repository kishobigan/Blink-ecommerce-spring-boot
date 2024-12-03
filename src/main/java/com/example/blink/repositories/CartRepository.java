package com.example.blink.repositories;

import com.example.blink.models.Cart;
import com.example.blink.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}

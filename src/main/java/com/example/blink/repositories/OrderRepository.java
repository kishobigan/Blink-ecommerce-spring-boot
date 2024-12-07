package com.example.blink.repositories;

import com.example.blink.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Orders, Long> {

}

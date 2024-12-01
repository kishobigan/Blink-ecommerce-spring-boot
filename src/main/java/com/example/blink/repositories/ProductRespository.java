package com.example.blink.repositories;

import com.example.blink.models.Category;
import com.example.blink.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRespository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category.CategoryId = :categoryId")
    List<Product> findProductByCategoryId(@Param("categoryId") Long categoryId);
}

package com.example.blink.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CategoryId;
    private String CategoryName;
    private String CategoryDescription;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> Products;

    public Category(Long categoryId, String categoryName, String categoryDescription, List<Product> products) {
        CategoryId = categoryId;
        CategoryName = categoryName;
        CategoryDescription = categoryDescription;
        Products = products;
    }

    public Category() {
        super();
    }

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }
}

package com.example.blink.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private double total;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CartItem> cartItems;


    public Cart(Long cartId, double total, User user, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.total = total;
        this.user = user;
        this.cartItems = cartItems;
    }

    public Cart() {
        super();
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

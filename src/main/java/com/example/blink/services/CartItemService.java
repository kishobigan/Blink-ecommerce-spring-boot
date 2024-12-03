package com.example.blink.services;

import com.example.blink.dto.CartItemRequest;
import com.example.blink.models.Cart;
import com.example.blink.models.CartItem;
import com.example.blink.models.Product;
import com.example.blink.repositories.CartItemRepository;
import com.example.blink.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    @Autowired
    private CartRepository cartRepository;

    public CartItemRequest createCartItem(String userId, CartItemRequest cartItemRequest) {
        // Retrieve the cart for the user
        Cart cart = cartService.getCart(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user ID: " + userId);
        }

        // Retrieve the product by ID
        Product product = productService.getProductById(Long.parseLong(cartItemRequest.getProductId()));
        if (product == null) {
            throw new RuntimeException("Product not found for ID: " + cartItemRequest.getProductId());
        }

        // Create a new CartItem
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProducts(List.of(product));
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setStatus(cartItemRequest.getStatus());
        cartItem.setCreatedAt(new Date());
        cartItem.setUpdatedAt(new Date());

        double totalPrice = product.getProductPrice() * cartItemRequest.getQuantity();
        cartItem.setTotalPrice(totalPrice);

        cartItemRepository.save(cartItem);

        cart.getCartItems().add(cartItem);
        double updatedTotalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItem::getTotalPrice).sum();

        cart.setTotal(updatedTotalPrice);
        cart.setTotal(updatedTotalPrice);
        cartRepository.save(cart);

        cartItemRequest.setTotalPrice(totalPrice);
        return cartItemRequest;
    }

    public Boolean removeCartItem(String userId, CartItemRequest cartItemRequest) {
        try {
            Cart cart = cartService.getCart(userId);
            if (cart == null) {
                throw new RuntimeException("Cart not found for user ID: " + userId);
            }

            Product product = productService.getProductById(Long.parseLong(cartItemRequest.getProductId()));
            if (product == null) {
                throw new RuntimeException("Product not found for ID: " + cartItemRequest.getProductId());
            }

            List<CartItem> cartItems = cart.getCartItems();
            cartItems.remove(product);
            cart.setCartItems(cartItems);

            double updatedTotalPrice = cartItems.stream()
                            .mapToDouble(CartItem::getTotalPrice).sum();

            cartRepository.save(cart);
            cart.setTotal(updatedTotalPrice);
            cartRepository.save(cart);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

}

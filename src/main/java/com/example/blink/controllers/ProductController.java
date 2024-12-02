package com.example.blink.controllers;

import com.example.blink.dto.ProductRequest;
import com.example.blink.models.Product;
import com.example.blink.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/product"))
public class ProductController {

    @Autowired
    private ProductService productService;


    private boolean isAdmin(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        return "admin".equalsIgnoreCase(role);
    }

    // Add Product
    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(HttpServletRequest request, @RequestBody ProductRequest product) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Update Product
    @PutMapping("/update-product")
    public ResponseEntity<Product> updateProduct(HttpServletRequest request, @RequestBody ProductRequest product) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Product UpdatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(UpdatedProduct, HttpStatus.OK);
    }

    // Delete Product
    @DeleteMapping("/delete-product")
    public ResponseEntity<Product> deleteProduct(HttpServletRequest request, @RequestParam Long id) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean deteltedStatus = productService.deleteProduct(id);
        if (deteltedStatus) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get Product by ID
    @GetMapping("/get-product")
    public ResponseEntity<?> getProduct(HttpServletRequest request,@RequestParam Long id) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    // List All Products
    @GetMapping("/list-products")
    public ResponseEntity<List<Product>> getAllProducts(HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // List Products by Category
    @GetMapping("/list-product-by-category")
    public ResponseEntity<List<Product>> getProductsByCategory(HttpServletRequest request,@RequestParam String id) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Product> products = productService.getProductsByCategory(id);
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }

}

package com.example.blink.services;

import com.example.blink.dto.ProductRequest;
import com.example.blink.models.Category;
import com.example.blink.models.Product;
import com.example.blink.repositories.CategoryRepository;
import com.example.blink.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRespository productRespository;

    @Autowired
    CategoryRepository categoryRepository;

    // Add Product
    public Product createProduct(ProductRequest product) {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setProductDescription(product.getProductDescription());
        newProduct.setProductPrice(product.getProductPrice());
        newProduct.setProductImage(product.getProductImage());
        newProduct.setQuantity(product.getQuantity());
        Optional<Category> categoryOptional = categoryRepository.findById(Long.parseLong(product.getCategory()));
        if (categoryOptional.isPresent()) {
            newProduct.setCategory(categoryOptional.get());
            productRespository.save(newProduct);
            return newProduct;
        }else {
            throw new IllegalArgumentException("Category not found");
        }

    }

    // Update Product
    public Product updateProduct(ProductRequest productRequest) {
        Product product = productRespository.findById(productRequest.getProductId()).get();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductImage(productRequest.getProductImage());
        Optional<Category> categoryOptional = categoryRepository.findById(Long.parseLong(productRequest.getCategory()));
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
            productRespository.save(product);
            return product;
        }else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    // Delete Product
    public boolean deleteProduct(Long productId) {
        productRespository.deleteById(productId);
        return true;
    }

    // Get Product by ID
    public Product getProductById(Long productId) {
        Product product = productRespository.findById(productId).orElse(null);
        return product;
    }

    // List All Products
    public List<Product> getAllProducts() {
        return productRespository.findAll();
    }

    // List Products by Category
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = productRespository.findProductByCategoryId(Long.parseLong(category));
        return products;
    }
}

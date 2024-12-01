package com.example.blink.controllers;

import com.example.blink.dto.CategoryRequest;
import com.example.blink.models.Category;
import com.example.blink.repositories.CategoryRepository;
import com.example.blink.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Helper method to check if the user has an admin role
    private boolean isAdmin(HttpServletRequest request) {
        String role = (String) request.getAttribute("role");
        return "admin".equalsIgnoreCase(role);
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(HttpServletRequest request, @RequestBody CategoryRequest categoryRequest) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>("Unauthorized: Only admins can create categories.", HttpStatus.UNAUTHORIZED);
        }
        Category createdCategory = categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/list-categories")
    public ResponseEntity<?> getAllCategories(HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>("Unauthorized: Only admins can view categories.", HttpStatus.UNAUTHORIZED);
        }
        List<Category> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/get-category")
    public ResponseEntity<?> getCategory(HttpServletRequest request, @RequestParam Long id) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>("Unauthorized: Only admins can view a category.", HttpStatus.UNAUTHORIZED);
        }
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update-category")
    public ResponseEntity<?> updateCategory(HttpServletRequest request, @RequestBody CategoryRequest categoryRequest) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>("Unauthorized: Only admins can update categories.", HttpStatus.UNAUTHORIZED);
        }
        Category updatedCategory = categoryService.updateCategory(categoryRequest);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete-category")
    public ResponseEntity<?> deleteCategory(HttpServletRequest request, @RequestParam Long id) {
        if (!isAdmin(request)) {
            return new ResponseEntity<>("Unauthorized: Only admins can delete categories.", HttpStatus.UNAUTHORIZED);
        }
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}

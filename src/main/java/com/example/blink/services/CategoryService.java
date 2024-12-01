package com.example.blink.services;

import com.example.blink.dto.CategoryRequest;
import com.example.blink.models.Category;
import com.example.blink.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setCategoryDescription(categoryRequest.getCategoryDescription());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateCategory(CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryRequest.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setCategoryDescription(categoryRequest.getCategoryDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }
}

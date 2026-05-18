package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private Long nextId = 1L;

    private List<Category> categories
            = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter( c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

//        if(category == null) {
//            return "Category is not found by this id";
//        }

        categories.remove(category);
        return "Category with Id: " + categoryId + "founded successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> categoryToUpdate =  categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if(categoryToUpdate.isPresent()){
            Category existingCategory = categoryToUpdate.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found");
        }

    }


}

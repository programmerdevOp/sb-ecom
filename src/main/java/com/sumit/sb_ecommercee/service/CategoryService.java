package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.model.Category;
import com.sumit.sb_ecommercee.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();

    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}

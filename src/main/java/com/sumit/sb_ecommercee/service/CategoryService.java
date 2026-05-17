package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void createCategory(Category category);
}

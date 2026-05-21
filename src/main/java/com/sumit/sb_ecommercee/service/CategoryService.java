package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.model.Category;
import com.sumit.sb_ecommercee.payload.CategoryDTO;
import com.sumit.sb_ecommercee.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}

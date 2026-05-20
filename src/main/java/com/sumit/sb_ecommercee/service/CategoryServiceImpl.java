package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.exception.APIException;
import com.sumit.sb_ecommercee.exception.ResourceNotFoundException;
import com.sumit.sb_ecommercee.model.Category;
import com.sumit.sb_ecommercee.payload.CategoryDTO;
import com.sumit.sb_ecommercee.payload.CategoryResponse;
import com.sumit.sb_ecommercee.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category created till now");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!!!");
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(categoryToDelete);
        return "Category with Id: " + categoryId + "founded successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory =  savedCategoryOptional.orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
        );

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }
}

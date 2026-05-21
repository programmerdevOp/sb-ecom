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
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        //Category savedCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());

//        Category category = modelMapper.map(categoryDTO, Category.class);
//        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
//
//        if(categoryFromDb != null){
//            throw new APIException("Category with the name: " + category.getCategoryName() + "already exists");
//        }
//
//        Category savedCategory = categoryRepository.save(category);
//        CategoryDTO savedCategoryDTO =  modelMapper.map(savedCategory, CategoryDTO.class);
//        return savedCategoryDTO;

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDB != null){
            throw new APIException("Category with category Name " + category.getCategoryName() + " already exist");
        }

        Category savedCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
        return savedCategoryDTO;

    }


    @Override
    public String deleteCategory(Long categoryId) {

        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(categoryToDelete);
        return "Category with Id: " + categoryId + "founded successfully";
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
        Category takeOutCategory = savedCategoryOptional.get();

//        Category savedCategory =  savedCategoryOptional.orElseThrow(
//                () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
//        );

        //category.setCategoryId(categoryId);
        //savedCategory = categoryRepository.save(category);
        //return savedCategory;

        takeOutCategory.setCategoryId(categoryDTO.getCategoryId());
        takeOutCategory.setCategoryName(categoryDTO.getCategoryName());

        Category savedCategory = categoryRepository.save(takeOutCategory);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
        return savedCategoryDTO;
    }
}

package com.sumit.sb_ecommercee.service;

import com.sumit.sb_ecommercee.exception.APIException;
import com.sumit.sb_ecommercee.exception.ResourceNotFoundException;
import com.sumit.sb_ecommercee.model.Category;
import com.sumit.sb_ecommercee.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private Long nextId = 1L;

    private CategoryRepository categoryRepository;

    //private List<Category> categories
    //        = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("Category is empty");
        }
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!!!");
        }
        //category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(categoryToDelete);
        return "Category with Id: " + categoryId + "founded successfully";
//        List<Category> categories = categoryRepository.findAll();
//        Category category = categories.stream()
//                .filter( c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

//        if(category == null) {
//            return "Category is not found by this id";
//        }

        //categories.remove(category);
//        categoryRepository.delete(category);
//        return "Category with Id: " + categoryId + "founded successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        Optional<Category> categoryToUpdate =  categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if(categoryToUpdate.isPresent()){
//            Category existingCategory = categoryToUpdate.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            return existingCategory;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found");
//        }

//        List<Category> categories =categoryRepository.findAll();
//        Optional<Category> categoryToUpdate = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if(categoryToUpdate.isPresent()){
//            Category existingCategory =  categoryToUpdate.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            return existingCategory;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
//        }
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory =  savedCategoryOptional.orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
        );

        //savedCategory.setCategoryName(category.getCategoryName());
        //categoryRepository.save(category);
        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;
    }


}

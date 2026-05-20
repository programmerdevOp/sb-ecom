package com.sumit.sb_ecommercee.repository;

import com.sumit.sb_ecommercee.model.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(@NotBlank String categoryName);

}

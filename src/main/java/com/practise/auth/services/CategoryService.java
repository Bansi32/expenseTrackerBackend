package com.practise.auth.services;

import com.practise.auth.dto.CategoryDTO;
import com.practise.auth.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<Category> findAllCategories();

    Optional<Category> findByCategoryId(Long id);

    Category findByCategoryName(String name);

    List<Category> getCategoriesByUserId(Long id);

    CategoryDTO updateCategory(Long id,CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}

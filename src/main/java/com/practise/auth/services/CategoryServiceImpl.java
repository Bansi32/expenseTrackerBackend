package com.practise.auth.services;

import com.practise.auth.dto.CategoryDTO;
import com.practise.auth.entity.Category;
import com.practise.auth.entity.User;
import com.practise.auth.mapper.CategoryMapper;
import com.practise.auth.mapper.UserMapper;
import com.practise.auth.repo.CategoryRepo;
import com.practise.auth.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Category> findAllCategories()
    {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findByCategoryId(Long id){
        return categoryRepo.findById(id);
    }
    @Override
    public Category findByCategoryName(String name)
    {
        return categoryRepo.findByName(name);
    }

    @Override
    public List<Category> getCategoriesByUserId(Long id)
    {
        return categoryRepo.findCategoriesByUserId(id);
    }
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
            Category category=categoryMapper.fromCategoryDto(categoryDTO);
            User user=userRepo.findById(categoryDTO.getUser().getId()).orElseThrow(()->new EntityNotFoundException("User with ID "+categoryDTO.getUser().getId()+" Not Found"));
            category.setUser(user);
            Category savedCategory=categoryRepo.save(category);
            HashSet<Category> cat=new HashSet<>();
            cat.add(category);
            user.setCategories(cat);
            return categoryMapper.fromCategory(savedCategory);
    }
    @Override
    public CategoryDTO updateCategory(Long id,CategoryDTO categoryDTO) {
        Category getCategory=categoryRepo.findById(id).orElseThrow(()->new EntityNotFoundException("CATEGORY with ID "+categoryDTO.getCategoryId()+" Not Found"));
        getCategory.setName(categoryDTO.getName());
        Category updatedCategory=categoryRepo.save(getCategory);
        return categoryMapper.fromCategory(updatedCategory);
    }
    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}

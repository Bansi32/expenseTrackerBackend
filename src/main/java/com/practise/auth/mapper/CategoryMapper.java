package com.practise.auth.mapper;

import com.practise.auth.dto.CategoryDTO;
import com.practise.auth.entity.Category;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    @Autowired
    private UserMapper userMapper;

    public CategoryDTO fromCategory(Category category)
    {
        CategoryDTO categoryDTO=new CategoryDTO();
        BeanUtils.copyProperties(category,categoryDTO);
        categoryDTO.setUser(userMapper.fromUsertoUserDto(category.getUser()));
        return categoryDTO;
    }
    public Category fromCategoryDto(CategoryDTO categoryDTO)
    {
        Category category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        return category;
    }

}

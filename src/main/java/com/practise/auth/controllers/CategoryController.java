package com.practise.auth.controllers;

import com.practise.auth.dto.CategoryDTO;
import com.practise.auth.entity.Category;
import com.practise.auth.mapper.CategoryMapper;
import com.practise.auth.services.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/categories")
    ResponseEntity<Collection<Category>> categories()
    {
        try
        {
            Collection<Category> allCategories=categoryService.findAllCategories();
            return new ResponseEntity<>(allCategories,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<List<Category>> getCategoryByUserId(@PathVariable Long id)
    {
        try{
            List<Category> categoryList= categoryService.getCategoriesByUserId(id);
            return new ResponseEntity<>(categoryList,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }
//    @GetMapping("/category/{id}")
//    ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id)
//    {
//        try{
//            Optional<Category> category= categoryService.findByCategoryId(id);
//            return new ResponseEntity<>(category,HttpStatus.ACCEPTED);
//        }
//        catch(Exception e)
//        {
//            throw new Error("Something went wrong!");
//        }
//    }
    @GetMapping("/category/{name}")
    ResponseEntity<Category> getCategoryByName(@PathVariable String name)
    {
        try{
            Category category= categoryService.findByCategoryName(name);
            return new ResponseEntity<>(category,HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }
    @PostMapping("/category")
    ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        try{
            CategoryDTO result=categoryService.createCategory(categoryDTO);
            return ResponseEntity.created(new URI("/api/category" + result.getCategoryId())).body(result);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }
    @PutMapping("/category/{id}")
    ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        try{
            CategoryDTO result=categoryService.updateCategory(id,categoryDTO);
            return ResponseEntity.created(new URI("/api/category" + result.getCategoryId())).body(result);
        }
        catch(Exception e)
        {
            throw new Error("Something went wrong!");
        }
    }
    @DeleteMapping("category/{id}")
    public void deleteCategory(@PathVariable Long id)
    {
        try{
            categoryService.deleteCategory(id);
        }
        catch(Exception e)
        {
            throw new Error();
        }
    }

}

package com.ensolvers.notes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensolvers.notes.models.dtos.CategoryInputDTO;
import com.ensolvers.notes.models.dtos.CategoryOutputDTO;
import com.ensolvers.notes.services.ICategoryService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryOutputDTO createCategory(@RequestBody CategoryInputDTO categoryInputDTO) {
        return categoryService.createCategory(categoryInputDTO);
    }

    @GetMapping
    public List<CategoryOutputDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "Category with id: " + id + " deleted";
    }
}

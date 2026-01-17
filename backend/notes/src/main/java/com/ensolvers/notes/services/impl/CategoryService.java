package com.ensolvers.notes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ensolvers.notes.models.dtos.CategoryInputDTO;
import com.ensolvers.notes.models.dtos.CategoryOutputDTO;
import com.ensolvers.notes.models.entities.Category;
import com.ensolvers.notes.repositories.ICategoryRepository;
import com.ensolvers.notes.services.ICategoryService;

@Service
public class CategoryService implements ICategoryService{

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryOutputDTO createCategory(CategoryInputDTO categoryInputDTO) {
        Category category = new Category();
        category.setName(categoryInputDTO.getName());
        Category savedCategory = categoryRepository.save(category);

        return categoryToOutputDTOMapper(savedCategory);
    }

    @Override
    public List<CategoryOutputDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryOutputDTO> categoryOutputDTOs = new ArrayList<>();

        for (Category category : categories) {
            CategoryOutputDTO dto = categoryToOutputDTOMapper(category);
            categoryOutputDTOs.add(dto);
        }

        return categoryOutputDTOs;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryOutputDTO categoryToOutputDTOMapper(Category category) {
        CategoryOutputDTO dto = new CategoryOutputDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    
}

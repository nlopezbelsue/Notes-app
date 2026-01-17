package com.ensolvers.notes.services;

import java.util.List;

import com.ensolvers.notes.models.dtos.CategoryInputDTO;
import com.ensolvers.notes.models.dtos.CategoryOutputDTO;

public interface ICategoryService {
    public CategoryOutputDTO createCategory(CategoryInputDTO categoryInputDTO);
    public List<CategoryOutputDTO> getAllCategories();
    public void deleteCategory(Long id);
}

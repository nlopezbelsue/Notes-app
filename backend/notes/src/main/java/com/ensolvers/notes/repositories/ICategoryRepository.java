package com.ensolvers.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensolvers.notes.models.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    
}

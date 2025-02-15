package com.example.demo.services;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.web.models.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private CategoryMapper categoryMapper;

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryResponse).toList();
    }

    public CategoryResponse getCategoryById(Long id) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found when trying to get."));
    return categoryMapper.categoryToCategoryResponse(category);
    }

    @Transactional
    public CategoryResponse createCategory(CategoryResponse categoryResponse) {
        Category category = categoryMapper.categoryResponseToCategory(categoryResponse);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(savedCategory);
    }

    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryResponse categoryResponse) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found when trying to update."));
        categoryMapper.updateEntityFromDto(categoryResponse, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(updatedCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found when trying to delete."));
        categoryRepository.delete(category);
    }
}

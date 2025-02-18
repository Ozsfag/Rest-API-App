package com.example.demo.services;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.web.models.CategoryRequest;
import com.example.demo.web.models.CategoryResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private CategoryMapper categoryMapper;

  public List<CategoryResponse> getAll() {
    return categoryRepository.findAll().stream()
        .map(categoryMapper::toResponse)
        .toList();
  }

  public CategoryResponse getCategoryById(Long id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found when trying to get."));
    return categoryMapper.toResponse(category);
  }

  @Transactional
  public CategoryResponse createCategory(CategoryRequest request) {
    Category category = categoryMapper.toEntity(request);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toResponse(savedCategory);
  }

  @Transactional
  public CategoryResponse updateCategory(Long id, CategoryRequest request) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found when trying to update."));
    categoryMapper.updateFromRequest(request, category);
    Category updatedCategory = categoryRepository.save(category);
    return categoryMapper.toResponse(updatedCategory);
  }

  @Transactional
  public void deleteCategory(Long id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found when trying to delete."));
    categoryRepository.delete(category);
  }
}

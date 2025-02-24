package com.example.demo.services;

import com.example.demo.exceptions.CategoryNonFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.web.models.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
  @Autowired private CategoryRepository categoryRepository;
  @Autowired private CategoryMapper categoryMapper;

  public Page<Category> getAll(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }

  public Category getCategoryById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(
            () ->
                new CategoryNonFoundException(
                    "Category with id: {} not found when trying to get."));
  }

  @Transactional
  public Category createCategory(CategoryRequest request) {
    Category category = categoryMapper.categoryRequestToCategory(request);
    return categoryRepository.save(category);
  }

  @Transactional
  public Category updateCategory(Long id, CategoryRequest request) {
    Category category = getCategoryById(id);
    Category updatedCategory = categoryMapper.updateEntityFromDto(request, category);
    return categoryRepository.save(updatedCategory);
  }

  @Transactional
  public void deleteCategory(Long id) {
    Category category = getCategoryById(id);
    categoryRepository.delete(category);
  }
}

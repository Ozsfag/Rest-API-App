package com.example.demo.web.controllers;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.services.CategoryService;
import com.example.demo.web.models.CategoryRequest;
import com.example.demo.web.models.CategoryResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  @Autowired private CategoryService categoryService;
  @Autowired private CategoryMapper categoryMapper;

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getAllCategories() {
    var response = categoryMapper.categoryListToCategoryResponseList(categoryService.getAll());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
    var response = categoryMapper.categoryToCategoryResponse(categoryService.getCategoryById(id));
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> createCategory(
      @Valid @RequestBody CategoryRequest request) {
    var response =
        categoryMapper.categoryToCategoryResponse(categoryService.createCategory(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> updateCategory(
      @PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
    var response =
        categoryMapper.categoryToCategoryResponse(categoryService.updateCategory(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}

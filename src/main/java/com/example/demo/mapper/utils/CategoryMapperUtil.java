package com.example.demo.mapper.utils;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("categoryMapperUtil")
@Component
@RequiredArgsConstructor
public class CategoryMapperUtil {
  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  @Named("getCategoryByCategoryId")
  public Category getCategoryByCategoryId(Long id) {
    return categoryMapper.categoryResponseToCategory(categoryService.getCategoryById(id));
  }
}

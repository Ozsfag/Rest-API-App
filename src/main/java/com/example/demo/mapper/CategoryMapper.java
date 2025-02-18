package com.example.demo.mapper;

import com.example.demo.models.Category;
import com.example.demo.web.models.CategoryRequest;
import com.example.demo.web.models.CategoryResponse;
import org.mapstruct.*;

/**
 * CategoryMapper is an interface for mapping between Category entity and CategoryResponse. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "id", source = "id")
  CategoryResponse toResponse(Category category);

  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "id", source = "id")
  Category toEntity(CategoryRequest request);

  void updateFromRequest(CategoryRequest request, @MappingTarget Category category);
}

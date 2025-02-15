package com.example.demo.mapper;

import com.example.demo.models.Author;
import com.example.demo.web.models.AuthorResponse;
import com.example.demo.web.models.CategoryResponse;
import com.example.demo.models.Category;
import org.mapstruct.*;

/**
 * CategoryMapper is an interface for mapping between Category entity and CategoryResponse. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

  /**
   * Maps a Category entity to a CategoryResponse.
   *
   * @param category the Category entity to be mapped
   * @return the mapped CategoryResponse
   */
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "id", source = "id")
  CategoryResponse categoryToCategoryResponse(Category category);

  /**
   * Maps a CategoryResponse to a Category entity.
   *
   * @param categoryResponse the CategoryResponse to be mapped
   * @return the mapped Category entity
   */
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "id", source = "id")
  Category categoryResponseToCategory(CategoryResponse categoryResponse);

  /**
   * Updates a Category entity from a CategoryResponse.
   *
   * @param request the CategoryResponse with updated values
   * @param category the Category entity to be updated
   */
  void updateEntityFromDto(CategoryResponse request, @MappingTarget Category category);
}

package com.example.demo.mapper;

import com.example.demo.models.Category;
import com.example.demo.web.models.CategoryRequest;
import com.example.demo.web.models.CategoryResponse;
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
  @Named("categoryToCategoryResponse")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "id", source = "id")
  CategoryResponse categoryToCategoryResponse(Category category);

  /**
   * Maps a CategoryResponse to a Category entity.
   *
   * @param request the CategoryResponse to be mapped
   * @return the mapped Category entity
   */
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "id", source = "id")
  Category categoryRequestToCategory(CategoryRequest request);

  @Named("categoryResponseToCategory")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  Category categoryResponseToCategory(CategoryResponse category);

  /**
   * Updates a Category entity from a CategoryRequest.
   *
   * @param request the CategoryRequest with updated values
   * @param category the Category entity to be updated
   */
  void updateEntityFromDto(CategoryRequest request, @MappingTarget Category category);
}

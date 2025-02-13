package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * CategoryMapper is an interface for mapping between Category entity and CategoryDto. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

  /**
   * Maps a Category entity to a CategoryDto.
   *
   * @param category the Category entity to be mapped
   * @return the mapped CategoryDto
   */
  CategoryDto mapToDto(Category category);

  /**
   * Maps a CategoryDto to a Category entity.
   *
   * @param categoryDto the CategoryDto to be mapped
   * @return the mapped Category entity
   */
  Category mapToEntity(CategoryDto categoryDto);
}

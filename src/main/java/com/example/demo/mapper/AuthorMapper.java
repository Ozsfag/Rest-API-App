package com.example.demo.mapper;

import com.example.demo.dto.AuthorDto;
import com.example.demo.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * AuthorMapper is an interface for mapping between Author entity and AuthorDto. It uses MapStruct
 * to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuthorMapper {

  /**
   * Maps an Author entity to an AuthorDto.
   *
   * @param author the Author entity to be mapped
   * @return the mapped AuthorDto
   */
  AuthorDto mapToDto(Author author);

  /**
   * Maps an AuthorDto to an Author entity.
   *
   * @param authorDto the AuthorDto to be mapped
   * @return the mapped Author entity
   */
  Author mapToEntity(AuthorDto authorDto);
}

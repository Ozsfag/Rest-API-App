package com.example.demo.mapper;

import com.example.demo.web.models.AuthorResponse;
import com.example.demo.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * AuthorMapper is an interface for mapping between Author entity and AuthorDto. It uses MapStruct
 * to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring")
public interface AuthorMapper {

  /**
   * Maps an Author entity to an AuthorDto.
   *
   * @param author the Author entity to be mapped
   * @return the mapped AuthorDto
   */
  @Mapping(target = "authorName", source = "username")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "id", source = "id")
  AuthorResponse ToDto(Author author);

  /**
   * Maps an AuthorDto to an Author entity.
   *
   * @param authorResponse the AuthorDto to be mapped
   * @return the mapped Author entity
   */
  @Mapping(target = "username", source = "authorName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "id", source = "id")
  Author ToEntity(AuthorResponse authorResponse);
}

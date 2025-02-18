package com.example.demo.mapper;

import com.example.demo.models.Author;
import com.example.demo.web.models.AuthorRequest;
import com.example.demo.web.models.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * AuthorMapper is an interface for mapping between Author entity and AuthorDto. It uses MapStruct
 * to automatically generate the implementation at compile time.
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {

  @Mapping(target = "authorName", source = "username")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "id", source = "id")
  AuthorResponse toResponse(Author author);

  @Mapping(target = "username", source = "authorName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "id", source = "id")
  Author toEntity(AuthorRequest authorRequest);

  void updateFromRequest(AuthorRequest request, @MappingTarget Author author);
}

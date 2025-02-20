package com.example.demo.mapper;

import com.example.demo.models.Author;
import com.example.demo.web.models.AuthorRequest;
import com.example.demo.web.models.AuthorResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * AuthorMapper is an interface for mapping between Author entity and AuthorDto. It uses MapStruct
 * to automatically generate the implementation at compile time.
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {

  /**
   * Maps an Author entity to an AuthorDto.
   *
   * @param author the Author entity to be mapped
   * @return the mapped AuthorResponse
   */
  @Named("authorToAuthorResponse")
  @Mapping(target = "authorName", source = "username")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "id", source = "id")
  AuthorResponse authorToAuthorResponse(Author author);

  /**
   * Maps an AuthorDto to an Author entity.
   *
   * @param authorRequest the AuthorDto to be mapped
   * @return the mapped Author entity
   */
  @Mapping(target = "username", source = "authorName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "comments", ignore = true)
  @Mapping(target = "news", ignore = true)
  Author authorRequestToAuthor(AuthorRequest authorRequest);

  @Named("authorResponseToAuthor")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "username", source = "authorName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "news", ignore = true)
  @Mapping(target = "comments", ignore = true)
  Author authorResponseToAuthor(AuthorResponse response);

  /**
   * Updates a Author entity from a AuthorResponse.
   *
   * @param request the AuthorResponse with updated values
   * @param author the Author entity to be updated
   */
  Author updateEntityFromDto(AuthorRequest request, @MappingTarget Author author);

  List<AuthorResponse> authoListToAuthorResponseList(List<Author> authors);
}

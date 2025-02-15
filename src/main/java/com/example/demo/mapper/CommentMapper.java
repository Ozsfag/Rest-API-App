package com.example.demo.mapper;

import com.example.demo.models.Author;
import com.example.demo.web.models.AuthorResponse;
import com.example.demo.web.models.CommentResponse;
import com.example.demo.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * CommentMapper is an interface for mapping between Comment entity and CommentDto. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    uses = {AuthorMapper.class})
public interface CommentMapper {

  /**
   * Maps a Comment entity to a CommentResponse.
   *
   * @param comment the Comment entity to be mapped
   * @return the mapped CommentResponse
   */
  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "authorName", source = "author.username")
  @Mapping(target = "newsId", source = "news.id")
  @Mapping(target = "newsTitle", source = "news.title")
  @Mapping(target = "newsContent", source = "news.content")
  @Mapping(target = "newsCreatedAt", source = "news.createdAt")
  @Mapping(target = "newsUpdatedAt", source = "news.updatedAt")

  CommentResponse commentToCommentResponse(Comment comment);

  /**
   * Maps a CommentResponse to a Comment entity. Ignores fields that are not present in the DTO.
   *
   * @param commentResponse the CommentResponse to be mapped
   * @return the mapped Comment entity
   */
  @Mapping(target = "author", source = "authorId")
  @Mapping(target = "news", source = "newsId")
  Comment commentResponseToComment(CommentResponse commentResponse);

  /**
   * Updates a Comment entity from a CommentResponse.
   *
   * @param request the CommentResponse with updated values
   * @param comment the Comment entity to be updated
   */
  void updateEntityFromDto(CommentResponse request, @MappingTarget Comment comment);
}

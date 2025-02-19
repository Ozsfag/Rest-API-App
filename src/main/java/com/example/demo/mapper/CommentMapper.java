package com.example.demo.mapper;

import com.example.demo.models.Comment;
import com.example.demo.web.models.CommentRequest;
import com.example.demo.web.models.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * CommentMapper is an interface for mapping between Comment entity and CommentDto. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    uses = {AuthorMapper.class, NewsMapper.class})
public interface CommentMapper {

  /**
   * Maps a Comment entity to a CommentResponse.
   *
   * @param comment the Comment entity to be mapped
   * @return the mapped CommentResponse
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "news", source = "news.id")
  @Mapping(target = "author", source = "author.id")
  @Mapping(target = "authorName", source = "author.username")
  CommentResponse commentToCommentResponse(Comment comment);

  /**
   * Maps a CommentResponse to a Comment entity. Ignores fields that are not present in the DTO.
   *
   * @param request the CommentResponse to be mapped
   * @return the mapped Comment entity
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "news", qualifiedByName = "newsMapperUtil.getNewsByNewsId", source = "newsId")
  @Mapping(
      target = "author",
      qualifiedByName = "authorMapperUtil.getAuthorByAuthorId",
      source = "authorId")
  Comment commentRequestToComment(CommentRequest request);

  /**
   * Updates a Comment entity from a CommentRequest.
   *
   * @param request the CommentResponse with updated values
   * @param comment the Comment entity to be updated
   */
  void updateEntityFromDto(CommentRequest request, @MappingTarget Comment comment);
}

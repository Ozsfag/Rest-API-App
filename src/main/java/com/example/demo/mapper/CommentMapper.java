package com.example.demo.mapper;

import com.example.demo.mapper.utils.AuthorMapperUtil;
import com.example.demo.mapper.utils.NewsMapperUtils;
import com.example.demo.models.Comment;
import com.example.demo.web.models.CommentRequest;
import com.example.demo.web.models.CommentResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * CommentMapper is an interface for mapping between Comment entity and CommentDto. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    uses = {AuthorMapper.class, NewsMapper.class, NewsMapperUtils.class, AuthorMapperUtil.class})
public interface CommentMapper {

  /**
   * Maps a Comment entity to a CommentResponse.
   *
   * @param comment the Comment entity to be mapped
   * @return the mapped CommentResponse
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "newsId", source = "news.id")
  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "authorName", source = "author.username")
  CommentResponse commentToCommentResponse(Comment comment);

  /**
   * Maps a CommentResponse to a Comment entity. Ignores fields that are not present in the DTO.
   *
   * @param request the CommentResponse to be mapped
   * @return the mapped Comment entity
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "content", source = "content")
  @Mapping(
      target = "news",
      qualifiedByName = {"newsMapperUtil", "getNewsByNewsId"},
      source = "newsId")
  @Mapping(
      target = "author",
      qualifiedByName = {"authorMapperUtil", "getAuthorByAuthorId"},
      source = "authorId")
  Comment commentRequestToComment(CommentRequest request);

  /**
   * Updates a Comment entity from a CommentRequest.
   *
   * @param request the CommentResponse with updated values
   * @param comment the Comment entity to be updated
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(
      target = "news",
      qualifiedByName = {"newsMapperUtil", "getNewsByNewsId"},
      source = "newsId")
  @Mapping(
      target = "author",
      qualifiedByName = {"authorMapperUtil", "getAuthorByAuthorId"},
      source = "authorId")
  Comment updateEntityFromDto(CommentRequest request, @MappingTarget Comment comment);

  @Named("commentListToCommentResponseList")
  List<CommentResponse> commentListToCommentResponseList(List<Comment> comments);

  @Named("commentResponseListToCommentList")
  List<Comment> commentResponseListToCommentList(List<CommentResponse> comments);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "content", source = "content")
  @Mapping(
      target = "news",
      qualifiedByName = {"newsMapperUtil", "getNewsByNewsId"},
      source = "newsId")
  @Mapping(
      target = "author",
      qualifiedByName = {"authorMapperUtil", "getAuthorByAuthorId"},
      source = "authorId")
  Comment commentResponseToComment(CommentResponse response);
}

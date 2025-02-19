package com.example.demo.mapper;

import com.example.demo.mapper.utils.AuthorMapperUtil;
import com.example.demo.mapper.utils.CategoryMapperUtil;
import com.example.demo.models.News;
import com.example.demo.web.models.NewsRequest;
import com.example.demo.web.models.NewsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * NewsMapper is an interface for mapping between News entity and its DTOs. It uses MapStruct to
 * automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    uses = {
      AuthorMapper.class,
      CategoryMapper.class,
      CommentMapper.class,
      AuthorMapperUtil.class,
      CategoryMapperUtil.class
    })
public interface NewsMapper {

  /**
   * Maps a NewsRequest to a News entity. Ignores fields that are not present in the request.
   *
   * @param request the NewsRequest to be mapped
   * @return the mapped News entity
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  @Mapping(
      target = "author",
      qualifiedByName = {"authorMapperUtil", "getAuthorByAuthorId"},
      source = "authorId")
  @Mapping(
      target = "category",
      qualifiedByName = {"categoryMapperUtil", "getCategoryByCategoryId"},
      source = "categoryId")
  @Mapping(target = "comments", ignore = true)
  News newsRequestToNews(NewsRequest request);

  /**
   * Maps a News entity to a NewsRequest.
   *
   * @param news the News entity to be mapped
   * @return the mapped NewsRequest
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "categoryId", source = "category.id")
  NewsRequest newsToNewsRequest(News news);

  /**
   * Maps a News entity to a NewsResponse. Calculates the comment count.
   *
   * @param news the News entity to be mapped
   * @return the mapped NewsResponse
   */
  @Named("newsToNewsResponse")
  @Mapping(target = "comments", ignore = true)
  @Mapping(target = "author", qualifiedByName = "authorToAuthorResponse", source = "author")
  @Mapping(target = "category", qualifiedByName = "categoryToCategoryResponse", source = "category")
  NewsResponse newsToNewsResponse(News news);

  /**
   * Maps a News entity to a NewsResponse including comments.
   *
   * @param news the News entity to be mapped
   * @return the mapped NewsResponse with comments
   */
  @Named("toResponseDtoWithComments")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "author", qualifiedByName = "authorToAuthorResponse", source = "author")
  @Mapping(target = "category", qualifiedByName = "categoryToCategoryResponse", source = "category")
  @Mapping(
      target = "comments",
      qualifiedByName = "commentListToCommentResponseList",
      source = "comments")
  NewsResponse newsToNewsResponseWithComments(News news);

  @Named("newsResponseToNews")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "author", qualifiedByName = "authorResponseToAuthor", source = "author")
  @Mapping(target = "category", qualifiedByName = "categoryResponseToCategory", source = "category")
  @Mapping(
      target = "comments",
      qualifiedByName = "commentResponseListToCommentList",
      source = "comments")
  News newsResponseToNews(NewsResponse response);

  /**
   * Updates a News entity from a NewsRequest.
   *
   * @param request the NewsRequest with updated values
   * @param news the News entity to be updated
   */
  void updateEntityFromDto(NewsRequest request, @MappingTarget News news);
}

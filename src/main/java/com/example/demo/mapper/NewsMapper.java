package com.example.demo.mapper;

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
    uses = {AuthorMapper.class, CategoryMapper.class})
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
  News newsRequestToNews(NewsRequest request);

  /**
   * Maps a News entity to a NewsRequest.
   *
   * @param news the News entity to be mapped
   * @return the mapped NewsRequest
   */
  @Mapping(target = "categoryId", source = "category.id")
  NewsRequest newsToNewsRequest(News news);

  /**
   * Maps a News entity to a NewsResponse. Calculates the comment count.
   *
   * @param news the News entity to be mapped
   * @return the mapped NewsResponse
   */
  @Mapping(
      target = "commentCount",
      expression = "java(news.getComments() != null ? (long) news.getComments().size() : 0L)")
  @Mapping(target = "comments", ignore = true)
  NewsResponse newsToNewsResponse(News news);

  /**
   * Maps a News entity to a NewsResponse including comments.
   *
   * @param news the News entity to be mapped
   * @return the mapped NewsResponse with comments
   */
  @Named("toResponseDtoWithComments")
  @Mapping(
      target = "commentCount",
      expression = "java(news.getComments() != null ? (long) news.getComments().size() : 0L)")
  NewsResponse newsToNewsResponseWithComments(News news);

  /**
   * Updates a News entity from a NewsRequest.
   *
   * @param request the NewsRequest with updated values
   * @param news the News entity to be updated
   */
  void updateEntityFromDto(NewsRequest request, @MappingTarget News news);
}

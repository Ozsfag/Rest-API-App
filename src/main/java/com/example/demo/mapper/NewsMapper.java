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
@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface NewsMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  News toEntity(NewsRequest request);

  @Mapping(target = "categoryId", source = "news.category.id")
  NewsRequest toRequest(News news);

  @Mapping(target = "commentCount", expression = "java(news.getComments() != null ? (long) news.getComments().size() : 0L)")
  @Mapping(target = "comments", ignore = true)
  NewsResponse toResponse(News news);

  @Named("toResponseWithComments")
  @Mapping(target = "commentCount", expression = "java(news.getComments() != null ? (long) news.getComments().size() : 0L)")
  NewsResponse toResponseWithComments(News news);

  void updateFromRequest(NewsRequest request, @MappingTarget News news);
}

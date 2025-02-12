package com.example.demo.mapper;

import com.example.demo.dto.NewsDto;
import com.example.demo.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {AuthorMapper.class, CategoryMapper.class})
public interface NewsMapper {
  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "authorName", source = "author.name")
  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  @Mapping(target = "commentCount", expression = "java(news.getComments().size())")
  NewsDto toDto(News news);

  @Mapping(target = "author", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "comments", ignore = true)
  News toEntity(NewsDto newsDto);
}

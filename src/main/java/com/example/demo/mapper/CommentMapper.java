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
@Mapper(componentModel = "spring", uses = {AuthorMapper.class, NewsMapper.class})
public interface CommentMapper {

  @Mapping(target = "authorId", source = "comment.author.id")
  @Mapping(target = "authorName", source = "comment.author.username")
  @Mapping(target = "newsId", source = "comment.news.id")
  @Mapping(target = "content", source = "content")
  CommentResponse toResponse(Comment comment);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "author", source = "author")
  @Mapping(target = "news", source = "news")
  Comment toEntity(CommentRequest request);

  void updateFromRequest(CommentRequest request, @MappingTarget Comment comment);
}

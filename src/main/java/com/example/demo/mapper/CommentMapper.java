package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {AuthorMapper.class})
public interface CommentMapper {
  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "authorName", source = "author.name")
  @Mapping(target = "newsId", source = "news.id")
  CommentDto toDto(Comment comment);

  @Mapping(target = "author", ignore = true)
  @Mapping(target = "news", ignore = true)
  Comment toEntity(CommentDto commentDto);
}

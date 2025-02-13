package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * CommentMapper is an interface for mapping between Comment entity and CommentDto. It uses
 * MapStruct to automatically generate the implementation at compile time.
 */
@Mapper(
    componentModel = "spring",
    uses = {AuthorMapper.class})
public interface CommentMapper {

  /**
   * Maps a Comment entity to a CommentDto.
   *
   * @param comment the Comment entity to be mapped
   * @return the mapped CommentDto
   */
  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "authorName", source = "author.username")
  @Mapping(target = "newsId", source = "news.id")
  CommentDto toDto(Comment comment);

  /**
   * Maps a CommentDto to a Comment entity. Ignores fields that are not present in the DTO.
   *
   * @param commentDto the CommentDto to be mapped
   * @return the mapped Comment entity
   */
  @Mapping(target = "author", ignore = true)
  @Mapping(target = "news", ignore = true)
  Comment toEntity(CommentDto commentDto);
}

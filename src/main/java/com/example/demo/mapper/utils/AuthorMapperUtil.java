package com.example.demo.mapper.utils;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.models.Author;
import com.example.demo.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("authorMapperUtil")
@Component
@RequiredArgsConstructor
public class AuthorMapperUtil {
  private final AuthorService authorService;
  private final AuthorMapper authorMapper;

  @Named("getAuthorByAuthorId")
  public Author getAuthorByAuthorId(Long id) {
    return authorMapper.authorResponseToAuthor(authorService.getAuthorById(id));
  }
}

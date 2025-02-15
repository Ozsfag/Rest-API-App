package com.example.demo.services;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.model.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.web.models.AuthorResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
  @Autowired private AuthorRepository authorRepository;
  @Autowired private AuthorMapper authorMapper;

  public List<AuthorResponse> getAll() {
    return authorRepository.findAll().stream().map(authorMapper::authorToAuthorResponse).toList();
  }

  public AuthorResponse getAuthorById(Long id) {
    Author author =
        authorRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Author now found when trying to get."));
    return authorMapper.authorToAuthorResponse(author);
  }

  @Transactional
  public AuthorResponse createAuthor(AuthorResponse authorResponse) {
    Author author = authorMapper.authorResponseToAuthor(authorResponse);
    author = authorRepository.save(author);
    return authorMapper.authorToAuthorResponse(author);
  }

  @Transactional
  public AuthorResponse updateAuthor(Long id, AuthorResponse authorResponse) {
    Author author =
        authorRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Author not found when trying to update."));
    authorMapper.updateEntityFromDto(authorResponse, author);
    author = authorRepository.save(author);
    return authorMapper.authorToAuthorResponse(author);
  }

  @Transactional
  public void deleteAuthor(Long id) {
    authorRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Author not found when trying to delete."));
    authorRepository.deleteById(id);
  }
}

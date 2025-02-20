package com.example.demo.services;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.web.models.AuthorRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
  @Autowired private AuthorRepository authorRepository;
  @Autowired private AuthorMapper authorMapper;

  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  public Author getAuthorById(Long id) {
    return authorRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Author now found when trying to get."));
  }

  @Transactional
  public Author createAuthor(AuthorRequest authorRequest) {
    Author author = authorMapper.authorRequestToAuthor(authorRequest);
    return authorRepository.save(author);
  }

  @Transactional
  public Author updateAuthor(Long id, AuthorRequest authorRequest) {
    Author author = getAuthorById(id);
    Author updatedAuthor = authorMapper.updateEntityFromDto(authorRequest, author);
    return authorRepository.save(updatedAuthor);
  }

  @Transactional
  public void deleteAuthor(Long id) {
    Author author = getAuthorById(id);
    authorRepository.delete(author);
  }
}

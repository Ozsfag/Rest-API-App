package com.example.demo.web.controllers;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.services.AuthorService;
import com.example.demo.web.models.AuthorRequest;
import com.example.demo.web.models.AuthorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
  @Autowired private AuthorService authorService;
  @Autowired private AuthorMapper authorMapper;

  @GetMapping
  public ResponseEntity<Page<AuthorResponse>> getAllAuthors(Pageable pageable) {
    var response = authorService.getAll(pageable).map(authorMapper::authorToAuthorResponse);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
    var response = authorMapper.authorToAuthorResponse(authorService.getAuthorById(id));
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request) {
    var response = authorMapper.authorToAuthorResponse(authorService.createAuthor(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorResponse> updateAuthor(
      @PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
    var response = authorMapper.authorToAuthorResponse(authorService.updateAuthor(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
    return ResponseEntity.noContent().build();
  }
}

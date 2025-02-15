package com.example.demo.web.controllers;

import com.example.demo.services.AuthorService;
import com.example.demo.web.models.AuthorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
  @Autowired private AuthorService authorService;

  @GetMapping
  public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
    return ResponseEntity.ok(authorService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
    return ResponseEntity.ok(authorService.getAuthorById(id));
  }

  @PostMapping
  public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorResponse author) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(author));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorResponse> updateAuthor(
      @PathVariable Long id, @Valid @RequestBody AuthorResponse author) {
    return ResponseEntity.ok(authorService.updateAuthor(id, author));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
    return ResponseEntity.noContent().build();
  }
}

package com.example.demo.web.controllers;

import com.example.demo.services.AuthorService;
import com.example.demo.web.models.AuthorRequest;
import com.example.demo.web.models.AuthorResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorResponse> updateAuthor(
      @PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
    return ResponseEntity.ok(authorService.updateAuthor(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
    return ResponseEntity.noContent().build();
  }
}

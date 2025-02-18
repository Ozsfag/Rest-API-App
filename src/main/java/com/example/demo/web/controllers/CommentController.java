package com.example.demo.web.controllers;

import com.example.demo.services.CommentService;
import com.example.demo.web.models.CommentRequest;
import com.example.demo.web.models.CommentResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
  @Autowired private CommentService commentService;

  @GetMapping
  public ResponseEntity<List<CommentResponse>> getAllComments() {
    return ResponseEntity.ok(commentService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
    return ResponseEntity.ok(commentService.getCommentById(id));
  }

  @PostMapping
  public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommentResponse> updateComment(
      @PathVariable Long id, @Valid @RequestBody CommentRequest request) {
    return ResponseEntity.ok(commentService.updateComment(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
    return ResponseEntity.noContent().build();
  }
}

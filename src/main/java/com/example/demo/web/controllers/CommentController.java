package com.example.demo.web.controllers;

import com.example.demo.mapper.CommentMapper;
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
  @Autowired private CommentMapper commentMapper;

  @GetMapping("/news/{newsId}")
  public ResponseEntity<List<CommentResponse>> getAllComments(@PathVariable Long newsId) {
    var response =
        commentMapper.commentListToCommentResponseList(commentService.getCommentsById(newsId));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
    var response = commentMapper.commentToCommentResponse(commentService.getCommentById(id));
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
    var response = commentMapper.commentToCommentResponse(commentService.createComment(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CommentResponse> updateComment(
      @PathVariable Long id, @Valid @RequestBody CommentRequest request) {
    var response =
        commentMapper.commentToCommentResponse(commentService.updateComment(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
    return ResponseEntity.noContent().build();
  }
}

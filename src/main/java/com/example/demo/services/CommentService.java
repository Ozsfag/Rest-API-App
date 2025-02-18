package com.example.demo.services;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.models.Comment;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.web.models.CommentRequest;
import com.example.demo.web.models.CommentResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
  @Autowired private CommentRepository commentRepository;
  @Autowired private CommentMapper commentMapper;

  public List<CommentResponse> getAll() {
    return commentRepository.findAll().stream()
        .map(commentMapper::toResponse)
        .toList();
  }

  public CommentResponse getCommentById(Long id) {
    return commentRepository
        .findById(id)
        .map(commentMapper::toResponse)
        .orElseThrow(() -> new RuntimeException("Comment not found when trying to get."));
  }

  @Transactional
  public CommentResponse createComment(CommentRequest request) {
    Comment comment = commentMapper.toEntity(request);
    Comment savedComment = commentRepository.save(comment);
    return commentMapper.toResponse(savedComment);
  }

  @Transactional
  public CommentResponse updateComment(Long id, CommentRequest request) {
    Comment comment =
        commentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found when trying to update."));
    commentMapper.updateFromRequest(request, comment);
    Comment updatedComment = commentRepository.save(comment);
    return commentMapper.toResponse(updatedComment);
  }

  @Transactional
  public void deleteComment(Long id) {
    Comment comment =
        commentRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found when trying to delete."));
    commentRepository.delete(comment);
  }
}

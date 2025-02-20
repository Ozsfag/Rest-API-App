package com.example.demo.services;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.models.Comment;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.web.models.CommentRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
  @Autowired private CommentRepository commentRepository;
  @Autowired private CommentMapper commentMapper;

  public List<Comment> getCommentsById(Long newsId) {
    return commentRepository.findByNewsId(newsId);
  }

  public Comment getCommentById(Long id) {
    return commentRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Comment not found when trying to get."));
  }

  @Transactional
  public Comment createComment(CommentRequest request) {
    Comment comment = commentMapper.commentRequestToComment(request);
    return commentRepository.save(comment);
  }

  @Transactional
  public Comment updateComment(Long id, CommentRequest request) {
    Comment comment = getCommentById(id);
    Comment updatedComment = commentMapper.updateEntityFromDto(request, comment);
    return commentRepository.save(updatedComment);
  }

  @Transactional
  public void deleteComment(Long id) {
    Comment comment = getCommentById(id);
    commentRepository.delete(comment);
  }
}

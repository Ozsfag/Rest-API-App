package com.example.demo.repositories;

import com.example.demo.models.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByNewsId(Long newsId);

  void deleteByNewsId(Long newsId);
}

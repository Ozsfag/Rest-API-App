package com.example.demo.repositories;

import com.example.demo.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
  Page<News> findByAuthorId(Long authorId, Pageable pageable);

  Page<News> findByCategoryId(Long id, Pageable pageable);
}

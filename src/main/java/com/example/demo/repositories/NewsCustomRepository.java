package com.example.demo.repositories;

import com.example.demo.models.News;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCustomRepository {
  Page<News> findNewsByFilters(NewsFilterCriteria newsFilterCriteria, Pageable pageable);
}

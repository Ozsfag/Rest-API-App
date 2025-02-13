package com.example.demo.repositories.impl;

import com.example.demo.model.News;
import com.example.demo.repositories.NewsCustomRepository;
import com.example.demo.repositories.NewsRepository;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import com.example.demo.repositories.specifications.NewsSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class NewsCustomRepositoryImpl implements NewsCustomRepository {

  @Autowired NewsRepository newsRepository;

  @Override
  public Page<News> findNewsByFilters(NewsFilterCriteria criteria, Pageable pageable) {
    return newsRepository.findAll(NewsSpecifications.withFilters(criteria), pageable);
  }
}

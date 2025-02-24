package com.example.demo.services;

import com.example.demo.annotations.Owner;
import com.example.demo.exceptions.NewsNotFoundException;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.models.News;
import com.example.demo.repositories.NewsRepository;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import com.example.demo.repositories.specifications.NewsSpecifications;
import com.example.demo.web.models.NewsRequest;
import jakarta.transaction.Transactional;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
  @Autowired private NewsRepository newsRepository;
  @Autowired private NewsMapper newsMapper;

  public Page<News> getAllNews(Pageable pageable) {
    return newsRepository.findAll(pageable);
  }

  public Page<News> getFilteredNews(NewsFilterCriteria criteria, Pageable pageable) {
    return newsRepository.findAll(NewsSpecifications.withFilters(criteria), pageable);
  }

  public News getNewsById(Long id) {
    return newsRepository
        .findById(id)
        .orElseThrow(() -> new NewsNotFoundException(MessageFormat.format("News with id: {} not found when trying to get.", id)));
  }

  @Transactional
  public News createNews(NewsRequest request) {
    News news = newsMapper.newsRequestToNews(request);
    return newsRepository.save(news);
  }

  @Owner(parameterName = "newsId")
  @Transactional
  public News updateNews(Long id, NewsRequest request) {
    News news = getNewsById(id);
    News updatedNews = newsMapper.updateEntityFromDto(request, news);
    return newsRepository.save(updatedNews);
  }

  @Owner(parameterName = "newsId")
  @Transactional
  public void deleteNews(Long id) {
    News news = getNewsById(id);
    newsRepository.delete(news);
  }
}

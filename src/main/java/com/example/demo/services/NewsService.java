package com.example.demo.services;

import com.example.demo.annotations.Owner;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.models.News;
import com.example.demo.repositories.NewsRepository;
import com.example.demo.web.models.NewsRequest;
import com.example.demo.web.models.NewsResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
  @Autowired private NewsRepository newsRepository;
  @Autowired private NewsMapper newsMapper;

  public Page<NewsResponse> getAllNews(Pageable pageable) {
    return newsRepository.findAll(pageable).map(newsMapper::newsToNewsResponse);
  }

  public News getNewsById(Long id) {
    return newsRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("News not found when trying to get."));
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

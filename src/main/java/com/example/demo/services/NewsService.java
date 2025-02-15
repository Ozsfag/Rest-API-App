package com.example.demo.services;

import com.example.demo.mapper.NewsMapper;
import com.example.demo.model.News;
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

  public NewsResponse getNewsById(Long id) {
    News news =
        newsRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("News not found when trying to get."));
    return newsMapper.newsToNewsResponseWithComments(news);
  }

  @Transactional
  public NewsResponse createNews(NewsRequest request) {
    News news = newsMapper.newsRequestToNews(request);
    News savedNews = newsRepository.save(news);
    return newsMapper.newsToNewsResponse(savedNews);
  }

  @Transactional
  public NewsResponse updateNews(Long id, NewsRequest request) {
    News news =
        newsRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("News not found when trying to update."));
    newsMapper.updateEntityFromDto(request, news);
    News updatedNews = newsRepository.save(news);
    return newsMapper.newsToNewsResponse(updatedNews);
  }

  @Transactional
  public void deleteNews(Long id) {
    News news =
        newsRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("News not found when trying to delete."));
    newsRepository.delete(news);
  }
}

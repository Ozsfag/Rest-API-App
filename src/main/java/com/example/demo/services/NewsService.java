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
    return newsRepository.findAll(pageable).map(newsMapper::toResponseDto);
  }

  public NewsResponse getNewsById(Long id) {
    News news =
        newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found."));
    return newsMapper.toResponseDtoWithComments(news);
  }

  @Transactional
  public NewsResponse createNews(NewsRequest request) {
    News news = newsMapper.toEntity(request);
    News savedNews = newsRepository.save(news);
    return newsMapper.toResponseDto(savedNews);
  }

  @Transactional
  public NewsResponse updateNews(Long id, NewsRequest request) {
    News news =
        newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found."));
    newsMapper.updateEntityFromDto(request, news);
    News updatedNews = newsRepository.save(news);
    return newsMapper.toResponseDto(updatedNews);
  }

  @Transactional
  public void deleteNews(Long id) {
    News news =
        newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found."));
    newsRepository.delete(news);
  }
}

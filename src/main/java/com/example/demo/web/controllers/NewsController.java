package com.example.demo.web.controllers;

import com.example.demo.mapper.NewsMapper;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import com.example.demo.services.NewsService;
import com.example.demo.web.models.NewsRequest;
import com.example.demo.web.models.NewsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {
  @Autowired private NewsService newsService;
  @Autowired private NewsMapper newsMapper;

  @GetMapping
  public ResponseEntity<Page<NewsResponse>> getAll(Pageable pageable) {
    var response = newsService.getAllNews(pageable).map(newsMapper::newsToNewsResponse);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/filter")
  public ResponseEntity<Page<NewsResponse>> getFilteredNEws(
      @Valid @RequestBody NewsFilterCriteria criteria, Pageable pageable) {
    var response =
        newsService.getFilteredNews(criteria, pageable).map(newsMapper::newsToNewsResponse);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NewsResponse> getById(@PathVariable Long id) {
    return ResponseEntity.ok(
        newsMapper.newsToNewsResponseWithComments(newsService.getNewsById(id)));
  }

  @PostMapping
  public ResponseEntity<NewsResponse> createNews(@Valid @RequestBody NewsRequest request) {
    NewsResponse createdNews = newsMapper.newsToNewsResponse(newsService.createNews(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
  }

  @PutMapping("/{id}")
  public ResponseEntity<NewsResponse> updateNews(
      @PathVariable Long id, @Valid @RequestBody NewsRequest request) {
    NewsResponse updatedNews = newsMapper.newsToNewsResponse(newsService.updateNews(id, request));
    return ResponseEntity.ok(updatedNews);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
    newsService.deleteNews(id);
    return ResponseEntity.noContent().build();
  }
}

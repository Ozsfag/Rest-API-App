package com.example.demo.mapper.utils;

import com.example.demo.models.News;
import com.example.demo.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("newsMapperUtil")
@Component
@RequiredArgsConstructor
public class NewsMapperUtils {
  private final NewsService newsService;

  @Named("getNewsByNewsId")
  public News getNewsByNewsId(Long id) {
    return newsService.getNewsById(id);
  }
}

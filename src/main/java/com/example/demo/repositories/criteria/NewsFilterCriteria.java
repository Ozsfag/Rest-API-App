package com.example.demo.repositories.criteria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsFilterCriteria {
  private String title;
  private Long authorId;
  private Long categoryId;
}

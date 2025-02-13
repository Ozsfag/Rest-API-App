package com.example.demo.repositories.criteria;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsFilterCriteria {
  private Long authorId;
  private Long categoryId;
  private String searchTerm;
  private Instant start;
  private Instant end;
}

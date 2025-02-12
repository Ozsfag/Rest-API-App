package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import lombok.Data;

@Data
public class NewsDto {

  private Long id;

  @NotBlank(message = "Title is required.")
  private String title;

  @NotBlank(message = "Content is required.")
  private String description;

  private Long authorId;
  private String authorName;
  private Long categoryId;
  private String categoryName;
  private int commentCount;
  private Instant CreatedAt;
  private Instant updatedAt;
}

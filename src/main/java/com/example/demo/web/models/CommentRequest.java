package com.example.demo.web.models;

import com.example.demo.models.Author;
import com.example.demo.models.News;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

  @NotNull(message = "Category id must be not null.")
  private Long id;

  @NotBlank(message = "Content is required.")
  private String content;

  @NotNull(message = "New's id must be not null.")
  private News news;

  @NotNull(message = "Author's id must be not null.")
  private Author author;

  @NotBlank(message = "Author's name  is required.")
  private String authorName;
}

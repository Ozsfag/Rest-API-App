package com.example.demo.web.models;

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
  private Long newsId;

  @NotNull(message = "Author's id must be not null.")
  private Long authorId;

  @NotBlank(message = "Author's name  is required.")
  private String authorName;
}

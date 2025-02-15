package com.example.demo.web.models;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import lombok.Data;

@Data
public class CommentResponse {

  private Long id;

  @NotBlank(message = "Content is required.")
  private String content;

  private Long newsId;
  private String authorId;
  private String authorName;
  private Instant createdAt;
  private Instant updatedAt;
}

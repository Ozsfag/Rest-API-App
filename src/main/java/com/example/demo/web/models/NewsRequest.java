package com.example.demo.web.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
  @NotNull(message = "News ID must be provided")
  private Long id;

  @NotBlank(message = "Title is required")
  @Size(max = 255, message = "Title must not exceed 255 characters")
  private String title;

  @NotBlank(message = "Content is required")
  @Size(max = 2000, message = "Content must not exceed 2000 characters")
  private String content;

  @NotNull(message = "Author ID must be provided")
  private Long authorId;

  @NotNull(message = "Category ID must be provided")
  private Long categoryId;
}

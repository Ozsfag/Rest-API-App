package com.example.demo.repositories.criteria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewsFilterCriteria {

  @Size(max = 255, message = "Title must be less than 255 characters")
  String title;

  @Min(value = 1, message = "Author ID must be greater than 0")
  Long authorId;

  @Min(value = 1, message = "Category ID must be greater than 0")
  Long categoryId;
}

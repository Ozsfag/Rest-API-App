package com.example.demo.web.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {

  @NotNull(message = "CategoryResponse id must be not null")
  private Long id;

  @NotBlank(message = "Category name is required.")
  private String name;

  @NotBlank(message = "Category description is required.")
  private String description;
}

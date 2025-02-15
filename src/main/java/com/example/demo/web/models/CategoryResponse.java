package com.example.demo.web.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryResponse {

  private Long id;

  @NotBlank(message = "Category name is required.")
  private String name;

  private String description;
}

package com.example.demo.web.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

  @NotBlank(message = "Category name is required.")
  private String name;

  @NotBlank(message = "Category description is required.")
  private String description;
}

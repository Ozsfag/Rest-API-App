package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

  private Long id;

  @NotBlank(message = "Category name is required.")
  private String name;

  private String description;
}

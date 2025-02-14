package com.example.demo.web.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorResponse {

  private Long id;

  @NotBlank(message = "Username is required.")
  private String authorName;

  @NotBlank(message = "Email is required.")
  @Email(message = "Invalid email format.")
  private String email;
}

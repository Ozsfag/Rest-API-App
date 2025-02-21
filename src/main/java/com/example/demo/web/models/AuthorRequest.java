package com.example.demo.web.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

  @NotBlank(message = "Username is required.")
  private String authorName;

  @NotBlank(message = "Email is required.")
  @Email(message = "Invalid email format.")
  private String email;
}

package com.example.demo.web.models;

import lombok.Data;

@Data
public class AuthorResponse {

  private Long id;
  private String authorName;
  private String email;
}

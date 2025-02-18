package com.example.demo.web.models;

import lombok.Data;

@Data
public class CommentResponse {

  private Long id;
  private String content;
  private Long newsId;
  private Long authorId;
  private String authorName;
}

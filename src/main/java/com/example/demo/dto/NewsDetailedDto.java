package com.example.demo.dto;

import java.util.List;

import com.example.demo.web.models.CommentResponse;
import lombok.Data;

@Data
public class NewsDetailedDto extends NewsDto {
  private List<CommentResponse> comments;
}

package com.example.demo.dto;

import java.util.List;
import lombok.Data;

@Data
public class NewsDetailedDto extends NewsDto {
  private List<CommentDto> comments;
}

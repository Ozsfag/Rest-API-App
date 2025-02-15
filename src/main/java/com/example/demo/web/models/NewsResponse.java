package com.example.demo.web.models;

import com.example.demo.dto.CommentDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
  private Long id;
  private String title;
  private String content;
  private AuthorResponse author;
  private CategoryDto category;
  private Long commentCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List<CommentDto> comments;

}

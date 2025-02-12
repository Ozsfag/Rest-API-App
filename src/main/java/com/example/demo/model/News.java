package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "news")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, length = 2000)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
  private List<Comment> comments;

  @Column(nullable = false)
  private Instant createdAt;

  @Column private Instant updatedAt;
}

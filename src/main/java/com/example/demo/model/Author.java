package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false, length = 15, unique = true)
  private String name;

  @Column(nullable = false, unique = true, length = 22)
  private String email;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<News> news;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Comment> comments;
}

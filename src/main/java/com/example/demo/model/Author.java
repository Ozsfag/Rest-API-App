package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  @Builder.Default
  private List<News> news = new ArrayList<>();

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  @Builder.Default
  private List<Comment> comments = new ArrayList<>();
}

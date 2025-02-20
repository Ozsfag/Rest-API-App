package com.example.demo.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "categories", schema = "app_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 15)
  private String name;

  @Column private String description;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  @Builder.Default
  private List<News> news = new ArrayList<>();
}

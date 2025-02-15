package com.example.demo.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

  @Column(nullable = false, unique = true, length = 15)
  private String name;

  @Column private String description;

  @OneToMany(mappedBy = "category")
  @Builder.Default
  private List<News> news = new ArrayList<>();
}

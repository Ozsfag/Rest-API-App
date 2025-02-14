package com.example.demo.repositories;

import com.example.demo.model.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  boolean existsByName(String name);

  Optional<Category> findByName(String name);
}

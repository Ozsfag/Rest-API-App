package com.example.demo.repositories;

import com.example.demo.models.Author;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
  boolean existsByName(String name);

  boolean existsByEmail(String email);

  Optional<Author> findByName(String name);
}

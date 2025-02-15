package com.example.demo.repositories.specifications;

import com.example.demo.models.News;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import java.time.Instant;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecifications {

  public static Specification<News> withFilters(NewsFilterCriteria criteria) {
    return Specification.<News>allOf()
        .and(hasAuthor(criteria.getAuthorId()))
        .and(hasCategory(criteria.getCategoryId()))
        .and(titleContains(criteria.getSearchTerm()))
        .and(createdBetween(criteria.getStart(), criteria.getEnd()));
  }

  private static Specification<News> hasAuthor(Long authorId) {
    return (root, query, cb) -> {
      if (authorId == null) {
        return cb.conjunction();
      }
      return cb.equal(root.get("author").get("id"), authorId);
    };
  }

  private static Specification<News> hasCategory(Long categoryId) {
    return (root, query, cb) -> {
      if (categoryId == null) {
        return cb.conjunction();
      }
      return cb.equal(root.get("category").get("id"), categoryId);
    };
  }

  private static Specification<News> titleContains(String searchTerm) {
    return (root, query, cb) -> {
      if (searchTerm == null || searchTerm.isEmpty()) {
        return cb.conjunction();
      }
      return cb.like(cb.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%");
    };
  }

  private static Specification<News> createdBetween(Instant start, Instant end) {
    return (root, query, cb) -> {
      if (start == null && end == null) {
        return cb.conjunction();
      }
      if (start == null) {
        return cb.lessThanOrEqualTo(root.get("createdAt"), end);
      }
      if (end == null) {
        return cb.greaterThanOrEqualTo(root.get("createdAt"), start);
      }
      return cb.between(root.get("createdAt"), start, end);
    };
  }
}

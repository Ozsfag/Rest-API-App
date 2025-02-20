package com.example.demo.repositories.specifications;

import com.example.demo.models.Author;
import com.example.demo.models.News;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecifications implements Specification<Author> {

  public static Specification<News> withFilters(NewsFilterCriteria criteria) {
    return Specification.<News>allOf()
        .and(hasAuthor(criteria.getAuthorId()))
        .and(hasCategory(criteria.getCategoryId()))
        .and(titleContains(criteria.getTitle()));
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

  @Override
  public Predicate toPredicate(
      Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    return null;
  }
}

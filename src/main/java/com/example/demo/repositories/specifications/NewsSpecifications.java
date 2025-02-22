package com.example.demo.repositories.specifications;

import com.example.demo.models.News;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class NewsSpecifications {

  public static Specification<News> withFilters(NewsFilterCriteria criteria) {
    return Specification.where(hasAuthor(criteria.getAuthorId()))
        .and(hasCategory(criteria.getCategoryId()))
        .and(titleContains(criteria.getTitle()));
  }

  private static Specification<News> hasAuthor(Long authorId) {
    return (root, _, cb) ->
        authorId == null ? cb.conjunction() : cb.equal(root.get("author").get("id"), authorId);
  }

  private static Specification<News> hasCategory(Long categoryId) {
    return (root, _, cb) ->
        categoryId == null
            ? cb.conjunction()
            : cb.equal(root.get("category").get("id"), categoryId);
  }

  private static Specification<News> titleContains(String searchTerm) {
    return (root, _, cb) ->
        (searchTerm == null || searchTerm.isEmpty())
            ? cb.conjunction()
            : cb.like(cb.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%");
  }
}

package com.example.demo.repositories.specifications;

import com.example.demo.model.News;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class NewsSpecifications {
    public static Specification<News> hasAuthor(Long authorId) {
        return (root,query,cb) -> {
            if (authorId == null)
                return null;
            return cb.equal(root.get("author").get("id"), authorId);
        };
    }

    public static Specification<News> hasCategory(Long categoryId) {
        return (root,query,cb) -> {
            if (categoryId == null)
                return null;
            return cb.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<News> titleCotains(String searchTerm) {
        return (root,query,cb) -> {
            if (searchTerm == null || searchTerm.isEmpty())
                return null;
            return cb.like(cb.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%");
        };
    }

    public static Specification<News> createdBetween(Instant start, Instant end) {
        return (root,query,cb) -> {
            if (start == null & end == null) {
                return null;
            }
            if (start == null) {
                return cb.lessThanOrEqualTo(root.get("createdAt"), end);
            }
        };
    }
}

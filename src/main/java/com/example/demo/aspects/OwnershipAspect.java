package com.example.demo.aspects;

import com.example.demo.annotations.Owner;
import com.example.demo.repositories.NewsCustomRepository;
import com.example.demo.repositories.criteria.NewsFilterCriteria;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

@Aspect
@Component
public class OwnershipAspect {
  @Autowired private NewsCustomRepository repository;

  @Before("@annotation(owner)")
  public void checkOwnership(JoinPoint joinPoint, Owner owner) throws AccessDeniedException {
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes != null) {
      HttpServletRequest request = attributes.getRequest();
      Map<String, String> pathVariables =
          (Map<String, String>)
              request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

      String userId = request.getHeader("User-Id"); // In real app, use proper authentication
      String resourceId = pathVariables.get(owner.parameterName());

      if (!isOwner(userId, resourceId)) {
        throw new AccessDeniedException("You don't have permission to modify this resource");
      }
    }
  }

  private boolean isOwner(String userId, String resourceId) {
    NewsFilterCriteria criteria =
        NewsFilterCriteria.builder()
            .authorId(Long.valueOf(userId))
            .categoryId(Long.valueOf(resourceId))
            .build();
    repository.findNewsByFilters(criteria);
    // Implement actual ownership check logic here
    return true; // Placeholder implementation
  }
}

package com.example.demo.aspects;

import com.example.demo.annotations.Owner;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.NewsRepository;
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

  @Autowired private NewsRepository newsRepository;
  @Autowired private CommentRepository commentRepository;

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

      if (resourceId != null && !isOwner(userId, resourceId, owner.parameterName())) {
        throw new AccessDeniedException("You don't have permission to modify this resource");
      }
    }
  }

  private boolean isOwner(String userId, String resourceId, String parameterName) {
    if ("newsId".equals(parameterName)) {
      return newsRepository
          .findById(Long.valueOf(resourceId))
          .map(news -> news.getAuthor().getId().equals(Long.valueOf(userId)))
          .orElse(false);
    } else if ("commentId".equals(parameterName)) {
      return commentRepository
          .findById(Long.valueOf(resourceId))
          .map(comment -> comment.getAuthor().getId().equals(Long.valueOf(userId)))
          .orElse(false);
    }
    return false;
  }
}

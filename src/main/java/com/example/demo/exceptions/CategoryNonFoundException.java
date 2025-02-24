package com.example.demo.exceptions;

public class CategoryNonFoundException extends RuntimeException {
  public CategoryNonFoundException(String message) {
    super(message);
  }
}

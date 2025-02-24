package com.example.demo.exceptions;

public class AuthorNotFoundException extends RuntimeException {
  public AuthorNotFoundException(String message) {
    super(message);
  }
}

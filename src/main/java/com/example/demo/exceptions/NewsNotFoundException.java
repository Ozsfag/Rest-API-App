package com.example.demo.exceptions;

public class NewsNotFoundException extends RuntimeException {
  public NewsNotFoundException(String message) {
    super(message);
  }
}

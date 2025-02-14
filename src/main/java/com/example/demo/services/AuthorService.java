package com.example.demo.services;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.model.Author;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.web.models.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired private AuthorRepository authorRepository;
    @Autowired private AuthorMapper authorMapper;

    public ResponseEntity<AuthorResponse> getAuthorById(Long id) {
    Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author now found."));
    return ResponseEntity.ok(authorMapper.ToDto(author));
    }
}

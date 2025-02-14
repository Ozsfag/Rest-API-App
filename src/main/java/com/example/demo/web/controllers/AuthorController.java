package com.example.demo.web.controllers;

import com.example.demo.repositories.AuthorRepository;
import com.example.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired private AuthorService authorService;

}

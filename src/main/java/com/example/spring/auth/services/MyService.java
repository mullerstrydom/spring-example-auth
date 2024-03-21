package com.example.spring.auth.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @PreAuthorize("hasRole('USER')")
    public String getTestContent() {
        return "Hello World from service";
    }

}

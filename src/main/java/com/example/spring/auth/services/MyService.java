package com.example.spring.auth.services;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String getTestContent() {
        return "Hello World from service";
    }

}

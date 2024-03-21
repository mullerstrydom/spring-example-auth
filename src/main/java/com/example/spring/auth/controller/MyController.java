package com.example.spring.auth.controller;

import com.example.spring.auth.services.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping
    public String test() {
        return myService.getTestContent();
    }

}

package com.omnigaurd.solilos.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@CrossOrigin(origins = "*")
public class Temp {
    @GetMapping("/health")
    public String health() {
        return "Working fine";
    }
}

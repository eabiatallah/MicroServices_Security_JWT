package com.eaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/gateway")
    public String helloWorld() {
        return "This is Gateway Service !";
    }
}

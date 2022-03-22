package com.example.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String hello(Principal principal) {
        return "home";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

package dev.jonkursani.restapigr2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller // ka me kthy HTML (faqe qe permban thymeleaf)
@RestController // ka me kthy JSON
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from my API";
    }
}
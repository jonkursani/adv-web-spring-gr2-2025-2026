package dev.jonkursani.restapigr2.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping
    public String get() {
        return "GET-ADMIN";
    }

    @PostMapping
    public String post() {
        return "POST-ADMIN";
    }

    @PutMapping
    public String put() {
        return "PUT-ADMIN";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE-ADMIN";
    }
}
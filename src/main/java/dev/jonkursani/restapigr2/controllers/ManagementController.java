package dev.jonkursani.restapigr2.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/management")
public class ManagementController {
    @GetMapping
    public String get() {
        return "GET-MANAGEMENT";
    }

    @PostMapping
    public String post() {
        return "POST-MANAGEMENT";
    }

    @PutMapping
    public String put() {
        return "PUT-MANAGEMENT";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE-MANAGEMENT";
    }
}
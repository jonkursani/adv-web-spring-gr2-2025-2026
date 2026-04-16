package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.annotations.AdminWrite;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping
    public String get() {
        return "GET-ADMIN";
    }

//    @PreAuthorize("hasAuthority('admin:write')")
    @AdminWrite
    @PostMapping
    public String post() {
        return "POST-ADMIN";
    }

//    @PreAuthorize("hasAuthority('admin:write')")
    @AdminWrite
    @PutMapping
    public String put() {
        return "PUT-ADMIN";
    }

    @PreAuthorize("hasAuthority('admin:write')")
    @AdminWrite
    @DeleteMapping
    public String delete() {
        return "DELETE-ADMIN";
    }
}
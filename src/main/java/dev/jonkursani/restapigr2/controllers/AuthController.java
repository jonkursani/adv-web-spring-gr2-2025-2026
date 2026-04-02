package dev.jonkursani.restapigr2.controllers;

import dev.jonkursani.restapigr2.dtos.auth.AuthResponse;
import dev.jonkursani.restapigr2.dtos.auth.LoginRequest;
import dev.jonkursani.restapigr2.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // autentifikimi i userit
        var user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        // gjenerimi i tokenit
        var token = authService.generateToken(user);

        // ndertimi i response-it
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .expiresIn(86400) // 24h in seconds
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
package com.sm.storagemanager.login.controller;

 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sm.storagemanager.security.dto.LoginRequest;
import com.sm.storagemanager.security.dto.LoginResponse;
import com.sm.storagemanager.security.service.impl.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
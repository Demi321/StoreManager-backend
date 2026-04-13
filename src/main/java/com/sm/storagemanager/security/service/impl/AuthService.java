package com.sm.storagemanager.security.service.impl;

import com.sm.storagemanager.security.dto.LoginRequest;
import com.sm.storagemanager.security.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authenticationRequest =
                new UsernamePasswordAuthenticationToken(
                        request.getBranchId() + "|" + request.getUsername(),
                        request.getPassword()
                );

        Authentication authenticationResult =
                authenticationManager.authenticate(authenticationRequest);

        String role = authenticationResult.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("SIN_ROL");

        return new LoginResponse(
                "Autenticacion correcta",
                request.getBranchId(),
                authenticationResult.getName(),
                role
        );
    }
}

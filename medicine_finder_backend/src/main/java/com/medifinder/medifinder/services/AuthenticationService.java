package com.medifinder.medifinder.services;

import com.medifinder.medifinder.dto.AuthenticatedRequest;
import com.medifinder.medifinder.dto.AuthenticatedResponse;
import com.medifinder.medifinder.repositories.UserRepository;
import com.medifinder.medifinder.configurations.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticatedResponse authenticate(AuthenticatedRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().toLowerCase(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail().toLowerCase())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticatedResponse().setToken(jwtToken);
    }
}

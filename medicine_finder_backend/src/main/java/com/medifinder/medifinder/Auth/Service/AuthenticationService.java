package com.medifinder.medifinder.Auth.Service;

import com.medifinder.medifinder.Auth.Dto.AuthenticatedRequest;
import com.medifinder.medifinder.Auth.Dto.AuthenticatedResponse;
import com.medifinder.medifinder.Auth.UserRepository;
import com.medifinder.medifinder.Utils.Config.JwtService;
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
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticatedResponse().setToken(jwtToken);
    }
}

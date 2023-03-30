package com.pasan.medifinder.cloud.oauth.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasan.medifinder.cloud.oauth.service.dtos.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthenticationConverter implements AuthenticationConverter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Authentication convert(HttpServletRequest request) {
        LoginRequest loginRequest = null;
        try {
            loginRequest = MAPPER.readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            return null;
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

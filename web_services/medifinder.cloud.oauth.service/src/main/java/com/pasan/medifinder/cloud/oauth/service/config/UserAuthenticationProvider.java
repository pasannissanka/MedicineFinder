package com.pasan.medifinder.cloud.oauth.service.config;

import com.pasan.medifinder.cloud.oauth.service.entities.AuthUser;
import com.pasan.medifinder.cloud.oauth.service.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.Collections;
import java.util.Optional;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<AuthUser> authUser = userRepository.findByUsername(username);

        if (authUser.isEmpty()) {
            return null;
        }

        AuthUser user = authUser.get();
        if (passwordEncoder.matches(CharBuffer.wrap(password), user.getPassword())) {
            // TODO user grant authorities
            return UsernamePasswordAuthenticationToken.authenticated(username, password, Collections.emptyList());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}

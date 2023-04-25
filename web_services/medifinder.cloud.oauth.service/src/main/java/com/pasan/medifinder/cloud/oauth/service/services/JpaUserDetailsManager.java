package com.pasan.medifinder.cloud.oauth.service.services;

import com.pasan.medifinder.cloud.oauth.service.entities.AuthUserDetails;
import com.pasan.medifinder.cloud.oauth.service.repositories.AuthUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailsManager implements UserDetailsManager {

    private final AuthUserDetailsRepository authUserDetailsRepository;
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();


    @Autowired
    public JpaUserDetailsManager(AuthUserDetailsRepository authUserDetailsRepository) {
        this.authUserDetailsRepository = authUserDetailsRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        authUserDetailsRepository.save((AuthUserDetails) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        authUserDetailsRepository.save((AuthUserDetails) user);
    }

    @Override
    public void deleteUser(String username) {
        AuthUserDetails userDetails = authUserDetailsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
        authUserDetailsRepository.delete(userDetails);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = this.securityContextHolderStrategy.getContext().getAuthentication();
        if (currentUser == null) {
            throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
        } else {
            AuthUserDetails userDetails = authUserDetailsRepository.findByUsername(currentUser.getName()).orElseThrow(
                    () -> new UsernameNotFoundException("Username not found")
            );
            userDetails.setPassword(newPassword);
            authUserDetailsRepository.save(userDetails);
        }

    }

    @Override
    public boolean userExists(String username) {
        return authUserDetailsRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUserDetails> userDetailsOptional = authUserDetailsRepository.findByUsername(username);
        if (userDetailsOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return userDetailsOptional.get();
    }
}

package com.pasan.medifinder.cloud.oauth.service.config;

import com.pasan.medifinder.cloud.oauth.service.repositories.AuthUserDetailsRepository;
import com.pasan.medifinder.cloud.oauth.service.services.JpaUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {

    private final AuthUserDetailsRepository authUserDetailsRepository;
    @Autowired
    public ApplicationSecurityConfig(AuthUserDetailsRepository authUserDetailsRepository) {
        this.authUserDetailsRepository = authUserDetailsRepository;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JpaUserDetailsManager(authUserDetailsRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider jpaDaoAuthenticationProvider() {
        DaoAuthenticationProvider jpaDaoAuthenticationProvider = new DaoAuthenticationProvider();
        jpaDaoAuthenticationProvider.setUserDetailsService(userDetailsService());
        jpaDaoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return jpaDaoAuthenticationProvider;
    }
}

package com.pasan.medifinder.cloud.oauth.service.config;

import com.pasan.medifinder.cloud.oauth.service.entities.AuthGrantedAuthority;
import com.pasan.medifinder.cloud.oauth.service.entities.AuthUserDetails;
import com.pasan.medifinder.cloud.oauth.service.repositories.AuthGrantedAuthorityRepository;
import com.pasan.medifinder.cloud.oauth.service.repositories.AuthUserDetailsRepository;
import com.pasan.medifinder.cloud.oauth.service.services.JpaRegisteredClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.*;


@Configuration
public class DBInitializerConfig {
    private final Logger logger = LoggerFactory.getLogger(DBInitializerConfig.class);
    private final JpaRegisteredClientRepository clientRepository;
    private final AuthUserDetailsRepository userDetailsRepository;
    private final AuthGrantedAuthorityRepository grantedAuthorityRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DBInitializerConfig(JpaRegisteredClientRepository clientRepository, AuthUserDetailsRepository userDetailsRepository,
                               AuthGrantedAuthorityRepository grantedAuthorityRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.grantedAuthorityRepository = grantedAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initializeJpaOAuthClientsData() {
        return (args) -> {
            logger.info("INITIALIZE JPA OAUTH CLIENT DATA");
            RegisteredClient messageClientRegistered = clientRepository.findByClientId("messaging-client");

            if (messageClientRegistered == null) {
                logger.info("CREATE NEW CLIENT [MESSAGING-CLIENT]");
                RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("messaging-client")
                        .clientSecret(passwordEncoder.encode("secret"))
                        .clientName("messaging-client")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                        .redirectUri("http://127.0.0.1:8080/authorized")
                        .scope(OidcScopes.PROFILE)
                        .scope(OidcScopes.OPENID)
                        .scope("message.read")
                        .scope("message.write")
                        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                        .build();

                logger.debug("CLIENT CREATED [MESSAGING-CLIENT] : {}", registeredClient);
                clientRepository.save(registeredClient);
            }
        };
    }

    @Bean
    public CommandLineRunner initializeJpaDefaultUserData() {
        return (args) -> {
            logger.info("INITIALIZE JPA DEFAULT USER DATA");
            Optional<AuthUserDetails> defaultUserOptional = userDetailsRepository.findByUsername("admin");

            if (defaultUserOptional.isEmpty()) {
                logger.debug("CREATE DEFAULT ADMIN USER");
                AuthUserDetails userDetails = new AuthUserDetails();
                userDetails.setUsername("admin");
                userDetails.setPassword(passwordEncoder.encode("123456"));
                userDetails.setEnabled(true);
                userDetails = userDetailsRepository.save(userDetails);

                AuthGrantedAuthority readGrantedAuthority = this.createGrantedAuthority("read", userDetails);
                AuthGrantedAuthority writeGrantedAuthority = this.createGrantedAuthority("write", userDetails);
                grantedAuthorityRepository.save(readGrantedAuthority);
                grantedAuthorityRepository.save(writeGrantedAuthority);

                userDetails.setAuthorities(Set.of(readGrantedAuthority, writeGrantedAuthority));
                userDetailsRepository.save(userDetails);
                logger.debug("DEFAULT ADMIN USER CREATED {}", userDetails);
            }
        };
    }

    private AuthGrantedAuthority createGrantedAuthority(String authority, AuthUserDetails userDetails) {
        logger.info("GRANTED AUTHORITY {}", authority);
        Optional<AuthGrantedAuthority> grantedAuthorityOptional = grantedAuthorityRepository.findByAuthority(authority);
        AuthGrantedAuthority grantedAuthority;
        if (grantedAuthorityOptional.isEmpty()) {
            logger.debug("CREATE GRANTED AUTHORITY {}", authority);
            grantedAuthority = new AuthGrantedAuthority();
            grantedAuthority.setAuthority(authority);
        } else {
            logger.debug("GRANTED AUTHORITY EXISTS {}", authority);
            grantedAuthority = grantedAuthorityOptional.get();
        }
        grantedAuthority.setAuthUserDetails(userDetails);
        logger.debug("GRANTED AUTHORITY {}", grantedAuthority);
        return grantedAuthority;
    }
}

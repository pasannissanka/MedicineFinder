package com.pasan.medifinder.cloud.oauth.service.services;

import com.pasan.medifinder.cloud.oauth.service.dtos.AuthUserDto;
import com.pasan.medifinder.cloud.oauth.service.dtos.request.CreateAuthUserRequest;
import com.pasan.medifinder.cloud.oauth.service.entities.AuthGrantedAuthority;
import com.pasan.medifinder.cloud.oauth.service.entities.AuthUserDetails;
import com.pasan.medifinder.cloud.oauth.service.exceptions.DuplicateEntityException;
import com.pasan.medifinder.cloud.oauth.service.repositories.AuthGrantedAuthorityRepository;
import com.pasan.medifinder.cloud.oauth.service.repositories.AuthUserDetailsRepository;
import com.pasan.medifinder.cloud.oauth.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final AuthUserDetailsRepository userDetailsRepository;
    private final AuthGrantedAuthorityRepository grantedAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AuthUserDetailsRepository userDetailsRepository, AuthGrantedAuthorityRepository grantedAuthorityRepository,
                       PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.grantedAuthorityRepository = grantedAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserDto create(CreateAuthUserRequest authUserData) throws DuplicateEntityException {
        if (userDetailsRepository.findByUsername(authUserData.getUsername()).isPresent()) {
            throw new DuplicateEntityException(Utils.USERNAME_TAKEN);
        }
        AuthUserDetails userDetails = new AuthUserDetails();
        userDetails.setUsername(authUserData.getUsername());
        userDetails.setPassword(passwordEncoder.encode(authUserData.getPassword()));
        userDetails.setEnabled(true); // TODO make this false until EMail verification completes
        userDetailsRepository.save(userDetails);

        Set<AuthGrantedAuthority> grantedAuthorities = createOrFindGrantedAuthority(authUserData.getAuthorities(), userDetails);
        userDetails.setAuthorities(grantedAuthorities);
        userDetailsRepository.save(userDetails);

        return new AuthUserDto().toAuthUserDto(userDetails);
    }

    private Set<AuthGrantedAuthority> createOrFindGrantedAuthority(String[] authorities, AuthUserDetails userDetails) {
        Set<AuthGrantedAuthority> mappedAuthorities =  Arrays.stream(authorities).map(authority -> {
            Optional<AuthGrantedAuthority> authorityOptional = grantedAuthorityRepository.findByAuthority(authority);
            AuthGrantedAuthority grantedAuthority;
            if (authorityOptional.isEmpty()) {
                grantedAuthority = new AuthGrantedAuthority();
                grantedAuthority.setAuthority(authority);
            } else {
                grantedAuthority = authorityOptional.get();
            }
            grantedAuthority.setAuthUserDetails(userDetails);

            return grantedAuthorityRepository.save(grantedAuthority);
        }).collect(Collectors.toSet());
        return mappedAuthorities;
    }
}

package com.pasan.medifinder.cloud.oauth.service.dtos;

import com.pasan.medifinder.cloud.oauth.service.entities.AuthUserDetails;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserDto {
    private String username;
    private List<String> authorities;
    private boolean enabled;

    public AuthUserDto toAuthUserDto(AuthUserDetails data) {
        return AuthUserDto.builder()
                .username(data.getUsername())
                .authorities(data.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .enabled(data.isEnabled())
                .build();
    }
}

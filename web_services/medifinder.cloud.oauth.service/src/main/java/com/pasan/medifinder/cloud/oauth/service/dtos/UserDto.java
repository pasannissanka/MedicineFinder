package com.pasan.medifinder.cloud.oauth.service.dtos;

import com.pasan.medifinder.cloud.oauth.service.entities.AuthUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;

    public UserDto toUserDto(AuthUser user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername()).build();
    }
}

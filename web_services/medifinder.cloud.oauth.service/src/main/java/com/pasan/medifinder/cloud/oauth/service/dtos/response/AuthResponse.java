package com.pasan.medifinder.cloud.oauth.service.dtos.response;

import com.pasan.medifinder.cloud.oauth.service.dtos.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private UserDto user;
    private String token;
}

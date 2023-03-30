package com.pasan.medifinder.cloud.oauth.service.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

//    TODO - Validation
    private String username;
    private String password;
}

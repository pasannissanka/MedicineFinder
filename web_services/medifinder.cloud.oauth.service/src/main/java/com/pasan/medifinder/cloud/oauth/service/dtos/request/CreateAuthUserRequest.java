package com.pasan.medifinder.cloud.oauth.service.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pasan.medifinder.cloud.oauth.service.entities.AuthGrantedAuthority;
import lombok.Data;

@Data
public class CreateAuthUserRequest {
    private String username;
    private String password;
    private String[] authorities;
}

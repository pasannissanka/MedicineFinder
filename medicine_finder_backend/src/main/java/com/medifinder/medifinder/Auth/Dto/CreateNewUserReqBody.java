package com.medifinder.medifinder.Auth.Dto;

import com.medifinder.medifinder.Auth.Model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewUserReqBody {
    private String email;
    private String password;
    private Role role;
}

package com.medifinder.medifinder.dto;

import com.medifinder.medifinder.entities.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewUserReqBody {
    private String email;
    private String password;
    private Role role;
}

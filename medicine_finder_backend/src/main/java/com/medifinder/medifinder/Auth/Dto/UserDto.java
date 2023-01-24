package com.medifinder.medifinder.Auth.Dto;

import com.medifinder.medifinder.Auth.Model.Role;
import com.medifinder.medifinder.Auth.Model.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private String email;
    private String id;
    private Role role;
    private Boolean verified;

    public UserDto toUserDto(User data) {
        return new UserDto()
                .setEmail(data.getEmail())
                .setId(data.getId())
                .setRole((data.getRole()))
                .setVerified(data.getVerified());
    }
}

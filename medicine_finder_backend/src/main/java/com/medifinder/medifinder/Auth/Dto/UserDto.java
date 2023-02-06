package com.medifinder.medifinder.Auth.Dto;

import com.medifinder.medifinder.Auth.Model.Role;
import com.medifinder.medifinder.Auth.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String id;
    private Role role;
    private Boolean verified;

    public UserDto toUserDto(User data) {
        return UserDto
                .builder()
                .email(data.getEmail())
                .id(data.getId())
                .role((data.getRole()))
                .verified(data.getVerified())
                .build();
    }
}

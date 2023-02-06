package com.medifinder.medifinder.dto;

import com.medifinder.medifinder.entities.Role;
import com.medifinder.medifinder.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

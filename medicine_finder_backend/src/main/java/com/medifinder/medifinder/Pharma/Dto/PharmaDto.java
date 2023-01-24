package com.medifinder.medifinder.Pharma.Dto;

import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Pharma.Models.Pharma;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PharmaDto {
    private String id;
    private UserDto user;
    private String details;

    public PharmaDto toPharmaDto(Pharma data) {
        return new PharmaDto()
                .setId(data.getId())
                .setUser(new UserDto().toUserDto(data.getUser()))
                .setDetails(data.getDetails());
    }
}

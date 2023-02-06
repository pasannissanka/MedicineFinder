package com.medifinder.medifinder.dto;

import com.medifinder.medifinder.entities.Pharma;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmaDto {
    private String id;
    private UserDto user;
    private String details;
    private String address;
    private String name;
    private Double lng;
    private Double lat;

    //    Lat = Y Long = X
    public PharmaDto toPharmaDto(Pharma data) {
        return PharmaDto.builder()
                .user(new UserDto().toUserDto(data.getUser()))
                .id(data.getId())
                .details(data.getDetails())
                .address(data.getAddress())
                .name(data.getName())
                .lng(data.getLocation().getX())
                .lat(data.getLocation().getY())
                .build();
    }

}

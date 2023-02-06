package com.medifinder.medifinder.Pharma.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Pharma.Models.Pharma;
import com.medifinder.medifinder.Pharma.PharmaRepository;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

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

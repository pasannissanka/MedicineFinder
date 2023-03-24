package com.medifinder.medifinder.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreatePharmaReqDto {

    private String email;
    private String password;
    private String name;
    private String details;
    private String address;
    private double lng;
    private double lat;
}

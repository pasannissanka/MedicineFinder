package com.medifinder.medifinder.dto.requests;

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

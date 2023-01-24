package com.medifinder.medifinder.Pharma.Dto;

import lombok.Data;

@Data
public class CreatePharmaReqDto {

    private String email;
    private String password;
    private String details;
}

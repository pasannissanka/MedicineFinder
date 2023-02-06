package com.medifinder.medifinder.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerReqDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}

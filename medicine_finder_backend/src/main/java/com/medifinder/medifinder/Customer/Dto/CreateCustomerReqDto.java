package com.medifinder.medifinder.Customer.Dto;

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

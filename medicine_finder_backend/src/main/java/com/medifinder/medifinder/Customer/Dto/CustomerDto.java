package com.medifinder.medifinder.Customer.Dto;

import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Customer.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;
    private UserDto user;

    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .user(new UserDto().toUserDto(customer.getUser())).build();
    }
}

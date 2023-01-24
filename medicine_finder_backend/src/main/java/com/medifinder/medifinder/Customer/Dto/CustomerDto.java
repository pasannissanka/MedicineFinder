package com.medifinder.medifinder.Customer.Dto;

import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Customer.Model.Customer;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;
    private UserDto user;

    public CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto().setId(customer.getId()).setFirstName(customer.getFirstName()).setLastName(customer.getLastName()).setUser(new UserDto().toUserDto(customer.getUser()));
    }
}

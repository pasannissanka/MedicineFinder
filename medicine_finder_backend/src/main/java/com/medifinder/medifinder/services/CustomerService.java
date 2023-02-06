package com.medifinder.medifinder.services;

import com.medifinder.medifinder.dto.requests.CreateCustomerReqDto;
import com.medifinder.medifinder.dto.CustomerDto;


public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerReqDto customerReqDto) throws Exception;

    CustomerDto findCustomerUser(String user_id) throws Exception;

}

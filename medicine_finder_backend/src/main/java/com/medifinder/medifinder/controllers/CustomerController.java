package com.medifinder.medifinder.controllers;

import com.medifinder.medifinder.services.CustomerService;
import com.medifinder.medifinder.dto.requests.CreateCustomerReqDto;
import com.medifinder.medifinder.dto.CustomerDto;
import com.medifinder.medifinder.dto.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Response<CustomerDto>> createCustomer(@RequestBody CreateCustomerReqDto reqDto) throws Exception {
        CustomerDto data = customerService.createCustomer(reqDto);
        return ResponseEntity.ok().body(Response.ok(data));
    }
}

package com.medifinder.medifinder.Customer;

import com.medifinder.medifinder.Customer.Dto.CreateCustomerReqDto;
import com.medifinder.medifinder.Customer.Dto.CustomerDto;
import com.medifinder.medifinder.Utils.Models.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseBody<CustomerDto> createCustomer(@RequestBody CreateCustomerReqDto reqDto) {
        try {
            CustomerDto data = customerService.createCustomer(reqDto);
            return new ResponseBody<CustomerDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<CustomerDto>().setError(ex.getMessage()).setMessage("ERROR");
        }
    }
}

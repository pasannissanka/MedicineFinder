package com.medifinder.medifinder.Auth;

import com.medifinder.medifinder.Auth.Dto.AuthenticatedRequest;
import com.medifinder.medifinder.Auth.Dto.AuthenticatedResponse;
import com.medifinder.medifinder.Auth.Dto.CreateNewUserReqBody;
import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Auth.Service.AuthenticationService;
import com.medifinder.medifinder.Auth.Service.UserService;
import com.medifinder.medifinder.Customer.Dto.CreateCustomerReqDto;
import com.medifinder.medifinder.Customer.Dto.CustomerDto;
import com.medifinder.medifinder.Customer.Service.CustomerService;
import com.medifinder.medifinder.Pharma.Dto.CreatePharmaReqDto;
import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Pharma.Service.PharmaService;
import com.medifinder.medifinder.Utils.Models.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PharmaService pharmaService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping
    public ResponseBody<UserDto> createUser(@RequestBody CreateNewUserReqBody body) {
        try {
            UserDto data = userService.createNewUser(body);
            return new ResponseBody<UserDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<UserDto>().setMessage("ERROR").setError(ex.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseBody<AuthenticatedResponse> authenticate(@RequestBody AuthenticatedRequest request) {
        return new ResponseBody<AuthenticatedResponse>().setMessage("SUCCESS").setData(authenticationService.authenticate(request));
    }

    @PostMapping("/customer")
    public ResponseBody<CustomerDto> createCustomer(@RequestBody CreateCustomerReqDto reqDto) {
        try {
            CustomerDto data = customerService.createCustomer(reqDto);
            return new ResponseBody<CustomerDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<CustomerDto>().setError(ex.getMessage()).setMessage("ERROR");
        }
    }

    @PostMapping("/pharma")
    public ResponseBody<PharmaDto> createPharmaUser(@RequestBody CreatePharmaReqDto reqDto) {
        try {
            PharmaDto data = pharmaService.createPharmaUser(reqDto);
            return new ResponseBody<PharmaDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<PharmaDto>().setError(ex.getMessage()).setMessage("ERROR");
        }
    }


}

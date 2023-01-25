package com.medifinder.medifinder.Auth;

import com.medifinder.medifinder.Auth.Dto.*;
import com.medifinder.medifinder.Auth.Model.Role;
import com.medifinder.medifinder.Auth.Repository.UserRepository;
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
import org.springframework.security.core.Authentication;
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
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseBody<?> me(Authentication authentication) {
        try {
            UserDto user = userService.findUserByEmail(authentication.getName());
            if (user.getRole().equals(Role.CUSTOMER)) {
                CustomerDto customerDto = customerService.findCustomerUser(user.getId());
                return new ResponseBody<CustomerDto>()
                        .setMessage("SUCCESS")
                        .setData(customerDto);
            } else {
                PharmaDto pharmaDto = pharmaService.findPharmaUser(user.getId());
                return new ResponseBody<PharmaDto>()
                        .setMessage("SUCCESS")
                        .setData(pharmaDto);
            }
        } catch (Exception ex) {
            return new ResponseBody<LoggedUserResponseDto>()
                    .setMessage("ERROR")
                    .setError(ex.getMessage());
        }
    }

    @PostMapping("/public/authenticate")
    public ResponseBody<AuthenticatedResponse> authenticate(@RequestBody AuthenticatedRequest request) {
        return new ResponseBody<AuthenticatedResponse>().setMessage("SUCCESS").setData(authenticationService.authenticate(request));
    }

    @PostMapping("/public/customer")
    public ResponseBody<CustomerDto> createCustomer(@RequestBody CreateCustomerReqDto reqDto) {
        try {
            CustomerDto data = customerService.createCustomer(reqDto);
            return new ResponseBody<CustomerDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<CustomerDto>().setError(ex.getMessage()).setMessage("ERROR");
        }
    }

    @PostMapping("/public/pharma")
    public ResponseBody<PharmaDto> createPharmaUser(@RequestBody CreatePharmaReqDto reqDto) {
        try {
            PharmaDto data = pharmaService.createPharmaUser(reqDto);
            return new ResponseBody<PharmaDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<PharmaDto>().setError(ex.getMessage()).setMessage("ERROR");
        }
    }
}

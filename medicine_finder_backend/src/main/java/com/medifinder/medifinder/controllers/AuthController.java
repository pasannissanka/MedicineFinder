package com.medifinder.medifinder.controllers;

import com.medifinder.medifinder.dto.AuthenticatedRequest;
import com.medifinder.medifinder.dto.AuthenticatedResponse;
import com.medifinder.medifinder.dto.UserDto;
import com.medifinder.medifinder.entities.Role;
import com.medifinder.medifinder.services.AuthenticationService;
import com.medifinder.medifinder.services.UserService;
import com.medifinder.medifinder.dto.requests.CreateCustomerReqDto;
import com.medifinder.medifinder.dto.CustomerDto;
import com.medifinder.medifinder.services.CustomerService;
import com.medifinder.medifinder.dto.requests.CreatePharmaReqDto;
import com.medifinder.medifinder.dto.PharmaDto;
import com.medifinder.medifinder.services.PharmaService;
import com.medifinder.medifinder.dto.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final PharmaService pharmaService;

    @Autowired
    private final AuthenticationService authenticationService;

    @GetMapping()
    public ResponseEntity<Response<?>> me(Authentication authentication) throws Exception {
        UserDto user = userService.findUserByEmail(authentication.getName());
        if (user.getRole().equals(Role.CUSTOMER)) {
            CustomerDto customerDto = customerService.findCustomerUser(user.getId());
            return ResponseEntity.ok().body(Response.ok(customerDto));
        } else {
            PharmaDto pharmaDto = pharmaService.findPharmaUser(user.getId());
            return ResponseEntity.ok().body(Response.ok(pharmaDto));
        }

    }

    @PostMapping("/public/authenticate")
    public ResponseEntity<Response<AuthenticatedResponse>> authenticate(@RequestBody AuthenticatedRequest request) {
        return ResponseEntity.ok().body(Response.ok(authenticationService.authenticate(request)));
    }

    @PostMapping("/public/customer")
    public ResponseEntity<Response<CustomerDto>> createCustomer(@RequestBody CreateCustomerReqDto reqDto) throws Exception {
        CustomerDto data = customerService.createCustomer(reqDto);
        return ResponseEntity.ok().body(Response.ok(data));
    }

    @PostMapping("/public/pharma")
    public ResponseEntity<Response<PharmaDto>> createPharmaUser(@RequestBody CreatePharmaReqDto reqDto) throws Exception {
        PharmaDto data = pharmaService.createPharmaUser(reqDto);
        return ResponseEntity.ok().body(Response.ok(data));
    }

    @PostMapping("/public/verify")
    public ResponseEntity<Response<Boolean>> verifyEmail(@RequestParam String token) throws Exception {
        Boolean updated = userService.verifyEmail(token);
        return ResponseEntity.ok().body(Response.ok(updated));
    }
}

package com.medifinder.medifinder.services;

import com.medifinder.medifinder.dto.CreateNewUserReqBody;
import com.medifinder.medifinder.repositories.CustomerRepository;
import com.medifinder.medifinder.entities.Role;
import com.medifinder.medifinder.entities.User;
import com.medifinder.medifinder.repositories.UserRepository;
import com.medifinder.medifinder.dto.requests.CreateCustomerReqDto;
import com.medifinder.medifinder.dto.CustomerDto;
import com.medifinder.medifinder.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    private final UserService userService;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public CustomerDto createCustomer(CreateCustomerReqDto customerReqDto) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(customerReqDto.getEmail().toLowerCase());

        if (existingUser.isPresent()) {
            throw new DuplicateKeyException("Email already taken");
        }

        User newUser = userService.createNewUser(CreateNewUserReqBody.builder()
                .email(customerReqDto.getEmail())
                .password(customerReqDto.getPassword())
                .role(Role.CUSTOMER)
                .build());

        Customer newCustomer = customerRepository.save(
                new Customer()
                        .setUser(newUser)
                        .setFirstName(customerReqDto.getFirstName())
                        .setLastName(customerReqDto.getLastName())
        );

        return new CustomerDto().toCustomerDto(newCustomer);
    }

    public CustomerDto findCustomerUser(String user_id) throws Exception {
        Optional<Customer> customer = customerRepository.findByUser_Id(user_id);
        if (customer.isEmpty())
            throw new Exception("User not found");
        return new CustomerDto().toCustomerDto(customer.get());
    }

}

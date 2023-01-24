package com.medifinder.medifinder.Customer.Service;

import com.medifinder.medifinder.Auth.Model.Role;
import com.medifinder.medifinder.Auth.Model.User;
import com.medifinder.medifinder.Auth.Repository.UserRepository;
import com.medifinder.medifinder.Customer.Dto.CreateCustomerReqDto;
import com.medifinder.medifinder.Customer.Dto.CustomerDto;
import com.medifinder.medifinder.Customer.Model.Customer;
import com.medifinder.medifinder.Customer.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public CustomerDto createCustomer(CreateCustomerReqDto customerReqDto) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail((customerReqDto.getEmail()));

        if (existingUser.isPresent()) {
            throw new Exception("Email already taken");
        }

        User newUser = userRepository.save(
                new User(
                        customerReqDto.getEmail(),
                        passwordEncoder.encode(customerReqDto.getPassword()),
                        Role.CUSTOMER
                )
        );

        Customer newCustomer = customerRepository.save(
                new Customer()
                        .setUser(newUser)
                        .setFirstName(customerReqDto.getFirstName())
                        .setLastName(customerReqDto.getLastName())
        );

        return new CustomerDto().toCustomerDto(newCustomer);
    }

}

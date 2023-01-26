package com.medifinder.medifinder.Customer;

import com.medifinder.medifinder.Customer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByUser_Id(String id);
}

package com.pasan.medifinder.cloud.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedifinderCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedifinderCustomerServiceApplication.class, args);
	}

}

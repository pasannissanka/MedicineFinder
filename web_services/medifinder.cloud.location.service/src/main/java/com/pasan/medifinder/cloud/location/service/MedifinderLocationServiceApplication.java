package com.pasan.medifinder.cloud.location.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedifinderLocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedifinderLocationServiceApplication.class, args);
	}

}

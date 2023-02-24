package com.pasan.medifinder.cloud.analytics.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedifinderAnalyticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedifinderAnalyticsServiceApplication.class, args);
	}

}

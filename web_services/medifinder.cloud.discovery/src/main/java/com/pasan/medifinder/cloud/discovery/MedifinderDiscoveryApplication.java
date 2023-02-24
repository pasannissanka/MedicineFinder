package com.pasan.medifinder.cloud.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MedifinderDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedifinderDiscoveryApplication.class, args);
	}

}

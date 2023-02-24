package com.pasan.medifinder.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MedifinderGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedifinderGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("customerModule", r -> r.path("/customers/**")
						.uri("lb://CUSTOMER-SERVICE"))
				.route("adminModule", r -> r.path("/admins/**")
						.uri("lb://ADMIN-SERVICE"))
				.route("locationModule", r -> r.path("/locations/**")
						.uri("lb://LOCATION-SERVICE"))
				.route("analyticsModule", r -> r.path("/analytics/**")
						.uri("lb://ANALYTICS-SERVICE"))
				.build();
	}
}

package com.example.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig
{
	   @Bean
	    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	        return builder.routes()
//	        		.route("TRAIN-DETAILS",r -> r.path("/**")
//	                         .uri("http://localhost:8085/**"))
	        		 .route("BOOKING-DETAILS",r -> r.path("/trainBooking/**")
	                         .uri("http://localhost:8086/trainBooking/**"))
//	        		 .route("DEMO",r -> r.path("/**")
//	                         .uri("http://localhost:8099/**"))
//	                .route("USERLOGIN", r -> r.path("/**")
//	                        .uri("http://localhost:8087/**"))
//	                .route("another_route", r -> r.path("/another")
//	                        .uri("http://another.com"))
	                // Add more routes as needed
	                .build();
	    }
}


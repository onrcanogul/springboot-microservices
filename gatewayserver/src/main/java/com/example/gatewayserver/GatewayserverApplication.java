package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/oogul/accounts/**")
						.filters(f -> f.rewritePath("/oogul/accounts/(?<segment>.*)","/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(p -> p.path("/oogul/loans/**")
						.filters(f -> f.rewritePath("/oogul/loans/(?<segment>.*)","/${segment}"))
						.uri("lb://LOANS"))
				.route(p -> p.path("/oogul/cards/**")
						.filters(f -> f.rewritePath("/oogul/cards/(?<segment>.*)","/${segment}"))
						.uri("lb://CARDS")).build();

	}

}

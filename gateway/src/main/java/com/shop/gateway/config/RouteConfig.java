package com.shop.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("user", route -> route.host("localhost:8080")
                        .and()
                        .path("/api/users", "/api/login")
                        .uri("http://localhost:8182"))
                .route("product", route -> route.host("localhost:8080")
                        .and()
                        .path("/api/products", "/api/categories")
                        .uri("http://localhost:8181"))
                .route("notification", route -> route.host("localhost:8080")
                        .and()
                        .path("/api/notifications", "/api/emails")
                        .uri("http://localhost:8183"))
                .build();
    }
}

package io.acleciocruz.service_cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCloudgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCloudgatewayApplication.class, args);
    }

    @Bean
    public RouteLocator router(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route.path("/clients/**").uri("lb://service-client"))
                .route(route -> route.path("/cards/**").uri("lb://service-card"))
                .route(route -> route.path("/appraiser-clients/**").uri("lb://service-appraiser"))
                .build();
    }

}

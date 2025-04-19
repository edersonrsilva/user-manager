package com.example.usermanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Manager API")
                        .description("Spring Boot REST API for managing users")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("User Manager Team")
                                .email("support@usermanager.com")
                                .url("https://www.usermanager.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
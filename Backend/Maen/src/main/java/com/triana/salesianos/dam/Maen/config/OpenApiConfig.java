package com.triana.salesianos.dam.Maen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MAEN Spring Boot 3 API -------")
                        .version("1.0")
                        .description("App to manage household finances Spring Boot 3 with Swagger")
                        .license(new License().name("Salesianos Triana Junior Fullstack Developers").url("https://github.com/ManuelMolina3/MAEN.git")));
    }

}

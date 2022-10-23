package ru.investing_portal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("${swagger.application-description}") String applicationDescription,
                                 @Value("${swagger.application-version}") String applicationVersion) {
        return new OpenAPI().info(new Info().title("Crypton API V1")
                .version(applicationVersion)
                .description(applicationDescription)
                .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org"))
                .contact(new Contact().name("developer")
                        .email("batishevmatveyy@gmail.com")));
    }

}

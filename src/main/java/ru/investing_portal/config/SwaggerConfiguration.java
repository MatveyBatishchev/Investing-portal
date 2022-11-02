package ru.investing_portal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Crypton API V1.2")
                .version("1.1")
                .description("""
                        Investing portal "Crypton" application interface endpoints documentation
                        
                        Важные особенности:
                         - Все дробные значения через точку
                         - При обновлении объектов можно внедрять не весь объект
                         - У пагинации есть значения по умолчанию
                         - При обновлении объекта нельзя изменить его связи (они будут игнорироваться), например изменить coinId в transaction
                         - Дату нужно передавать в необходимом формате. Например: 2022-10-27T15:52:56.261Z""")
                .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org"))
                .contact(new Contact().name("developer")
                        .email("batishevmatveyy@gmail.com")));
    }

}

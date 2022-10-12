package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients(basePackages = {"ru.investing_portal.feign"})
@EnableJpaRepositories("ru.investing_portal.repos")
@EntityScan("ru.investing_portal.models.domain")
@ComponentScan(basePackages = {"ru.investing_portal.*"})
public class InvestingPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestingPortalApplication.class, args);
    }

}

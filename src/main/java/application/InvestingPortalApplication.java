package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages={"ru.investing_portal.feign"})
@ComponentScan(basePackages = {"ru.investing_portal.*"})
public class InvestingPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestingPortalApplication.class, args);
    }

}

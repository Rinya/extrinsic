package ru.alfastrah.alfadigital.extrinsic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("ru.alfastrah.alfadigital")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


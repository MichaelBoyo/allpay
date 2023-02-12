package com.tbthecoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PayStackApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayStackApplication.class, args);
    }
}
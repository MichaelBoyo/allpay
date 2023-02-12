package com.edenlifemock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlutterwaveApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlutterwaveApplication.class,args);
    }
}

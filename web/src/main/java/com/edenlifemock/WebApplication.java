package com.edenlifemock;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.edenlifemock.clients"
)
@EnableAsync
@EnableScheduling
public class WebApplication {
    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class,args);
    }
}

package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Payment8300NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(Payment8300NacosApplication.class, args);
    }
}

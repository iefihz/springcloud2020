package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author He Zhifei
 * @date 2020/7/14 17:11
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigClient5002BusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient5002BusApplication.class, args);
    }
}

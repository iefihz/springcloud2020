package com.iefihz.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author He Zhifei
 * @date 2020/7/14 14:39
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigClient4001Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient4001Application.class, args);
    }
}

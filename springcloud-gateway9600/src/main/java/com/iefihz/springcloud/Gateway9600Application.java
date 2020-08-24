package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author He Zhifei
 * @date 2020/7/14 0:50
 */
@EnableEurekaClient
@SpringBootApplication
public class Gateway9600Application {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9600Application.class, args);
    }
}

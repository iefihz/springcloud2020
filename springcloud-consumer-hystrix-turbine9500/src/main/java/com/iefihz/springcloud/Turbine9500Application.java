package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author He Zhifei
 * @date 2020/7/13 19:49
 */
@EnableEurekaClient
@EnableTurbine
@SpringBootApplication
public class Turbine9500Application {
    public static void main(String[] args) {
        SpringApplication.run(Turbine9500Application.class, args);
    }
}

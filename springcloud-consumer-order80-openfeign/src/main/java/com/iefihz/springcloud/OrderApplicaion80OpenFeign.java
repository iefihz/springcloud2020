package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author He Zhifei
 * @date 2020/7/11 21:49
 */
@EnableFeignClients
@SpringBootApplication
public class OrderApplicaion80OpenFeign {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicaion80OpenFeign.class, args);
    }
}

package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author He Zhifei
 * @date 2020/7/8 15:20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentZKApplication8100 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZKApplication8100.class, args);
    }
}

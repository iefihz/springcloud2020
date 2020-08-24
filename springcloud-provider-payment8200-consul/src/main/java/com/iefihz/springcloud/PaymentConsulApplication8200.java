package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author He Zhifei
 * @date 2020/7/8 18:39
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentConsulApplication8200 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulApplication8200.class, args);
    }
}

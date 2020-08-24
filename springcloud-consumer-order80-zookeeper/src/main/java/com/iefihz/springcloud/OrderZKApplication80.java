package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author He Zhifei
 * @date 2020/7/8 18:02
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderZKApplication80 {

    @Bean
    @LoadBalanced
    public RestTemplate template() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderZKApplication80.class, args);
    }
}

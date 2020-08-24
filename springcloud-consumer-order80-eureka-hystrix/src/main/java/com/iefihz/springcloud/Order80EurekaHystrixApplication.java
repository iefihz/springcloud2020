package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author He Zhifei
 * @date 2020/7/13 14:44
 */
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
public class Order80EurekaHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(Order80EurekaHystrixApplication.class, args);
    }
}

package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author He Zhifei
 * @date 2020/7/7 15:48
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication7001.class, args);
    }
}

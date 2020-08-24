package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author He Zhifei
 * @date 2020/7/14 13:33
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigServer3001Application {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer3001Application.class, args);
    }
}

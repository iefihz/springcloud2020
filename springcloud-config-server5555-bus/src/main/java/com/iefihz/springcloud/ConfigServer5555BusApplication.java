package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author He Zhifei
 * @date 2020/7/14 17:00
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServer5555BusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer5555BusApplication.class, args);
    }
}

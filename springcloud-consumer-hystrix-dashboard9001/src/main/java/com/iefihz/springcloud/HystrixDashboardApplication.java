package com.iefihz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * http://localhost:9001/hystrix 访问监控界面
 *
 * @author He Zhifei
 * @date 2020/7/13 18:38
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}

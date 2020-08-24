package com.iefihz.springcloud;

import com.iefihz.ribbonRule.CusRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author He Zhifei
 * @date 2020/7/7 14:07
 */
@RibbonClient(name = "SPRINGCLOUD-PAYMENT-SERVICE", configuration = CusRuleConfig.class)
@EnableEurekaClient
@SpringBootApplication
public class OrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class, args);
    }
}

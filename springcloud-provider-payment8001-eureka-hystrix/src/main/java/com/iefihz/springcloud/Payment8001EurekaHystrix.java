package com.iefihz.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author He Zhifei
 * @date 2020/7/13 13:06
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class Payment8001EurekaHystrix {
    public static void main(String[] args) {
        SpringApplication.run(Payment8001EurekaHystrix.class, args);
    }

    /**
     * 配置这个才能在9001的dashboard监控界面输入：http://localhost:8001/hystrix.stream 才有数据，springboot 2.x默认是/actuator/hystrix.stream，并且要配置yml
     * @return
     */
//    @Bean
//    public ServletRegistrationBean getServlet() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }
}

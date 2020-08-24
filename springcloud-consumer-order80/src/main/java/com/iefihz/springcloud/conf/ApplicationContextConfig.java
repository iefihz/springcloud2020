package com.iefihz.springcloud.conf;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author He Zhifei
 * @date 2020/7/7 14:12
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * @LoadBalanced 开启ribbon实现的负载均衡，此时，用RestTemplate只能通过服务名称去访问，不能通过ip地址去访问（因为ribbon内部会做负载均衡，然后再访问）
     * 但是，在使用自定义负载均衡策略RoundRobinLoadBalancer时，要注释这个注解，因为RootLoadBalancer.instance()获取到的已经是某一个微服务的实例了，
     * 只能通过这个微服务实例的url去请求对应的实例，获取响应数据。如果不注释这个注解的话，ribbon会把这个uri当做服务名称，再去eureka server中查找，结果显然是
     * 不存在的。
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

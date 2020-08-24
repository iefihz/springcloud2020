package com.iefihz.springcloud.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

/**
 * 实现这个接口来实现负载均衡算法，实现类需要使用@Component注解，并在对应的controller里注入使用
 *
 * @author He Zhifei
 * @date 2020/7/9 1:09
 */
public interface RootLoadBalancer {

    /**
     * 通过serviceId获取该微服务的具体某个实例，规则自己定
     * @param serviceId
     * @return 微服务集群的某个特定实例
     */
    ServiceInstance instance(String serviceId);
}

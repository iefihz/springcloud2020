package com.iefihz.springcloud.loadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟实现轮询的负载均衡算法
 *
 * @author He Zhifei
 * @date 2020/7/9 1:12
 */
@Component
public class RoundRobinLoadBalancer implements RootLoadBalancer {

    @Autowired
    private DiscoveryClient discoveryClient;

    private AtomicInteger total = new AtomicInteger(0);

    /**
     * CAS+自旋锁
     * @param instancesSize
     * @return
     */
    private final int getServiceInstanceIndex(int instancesSize) {
        int current;
        int next;
        do {
            current = this.total.get();
            next = (current >= Integer.MAX_VALUE ? 0 : current + 1) % instancesSize;
        } while (!this.total.compareAndSet(current, next));
        return next;
    }

    @Override
    public final ServiceInstance instance(String serviceId) {
        if (StringUtils.isEmpty(serviceId)) return null;
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

        int size = instances.size();
        if (size <= 0) {
            return null;
        } else if (size == 1) {
            return instances.get(0);
        } else {
            //获取下标，通过下标返回具体的ServiceInstance
            return instances.get(getServiceInstanceIndex(size));
        }
    }
}

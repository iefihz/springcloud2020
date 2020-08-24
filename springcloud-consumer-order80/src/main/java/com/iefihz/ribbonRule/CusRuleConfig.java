package com.iefihz.ribbonRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 替换默认的Ribbon负载均衡规则
 *
 * 这个类不能放在与启动类同级的位置，也不能放在其下的其他包下，
 * 因为不能被@ComponentScan注解扫描到，而启动类上使用了@SpringBootApplication，里面有该注解，
 * 如果被扫描到，则ribbon对于所有微服务的集群都是使用这种算法，不能达到定制化的效果（某个微服务集群，使用特定的算法）
 *
 * <p>
 *      默认的轮询规则的算法：从服务器启动开始算，每次请求进来+1，统计当前请求是第几次，然后通过：
 * 总请求数n % 指定微服务的实例数m = 实例下标i       通过：
 * List<ServiceInstance> instances = discoveryClient.getInstances(service); 获取总实例，通过instances.get(i)获取最终调用的是哪个实例
 * </p>
 *
 * 用法：配置完这个配置类后，在启动类上使用
 * @RibbonClient(name = "SPRINGCLOUD-PAYMENT-SERVICE", configuration = CusRuleConfig.class)
 * name指定对哪个微服务使用特定的配置规则，configuration指定规则的配置类
 * 配置好后，代表当前微服务调用名称为SPRINGCLOUD-PAYMENT-SERVICE的微服务的负载均衡算法为配置类里配置的算法
 *
 * @author He Zhifei
 * @date 2020/7/9 0:03
 */
@Configuration
public class CusRuleConfig {

    /**
     * 替换默认的RoundRobinRule（轮询规则）
     * @return
     */
    @Bean
    public IRule cusRule() {
        return new RandomRule();
    }
}

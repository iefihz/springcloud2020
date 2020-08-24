package com.iefihz.springcloud.controller;

import com.iefihz.springcloud.conf.econf.ResultVO;
import com.iefihz.springcloud.entity.Payment;
import com.iefihz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author He Zhifei
 * @date 2020/7/7 12:15
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //获取注册中心的注册信息，不要导错包了
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping
    public int add(@RequestBody @Validated Payment payment) {
        log.info("请求进入PaymentController.add(Payment)");
        return paymentService.add(payment);
    }

    @GetMapping("/{id:[0-9]+}")
    public Payment getById(@PathVariable("id") Long id) {
        log.info("请求进入PaymentController.getById(Payment)");
        log.info("具体调用的微服务的端口号是：{}", port);
        return paymentService.getById(id);
    }

    @GetMapping("/discovery")
    public void serviceDiscovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            service = service.toUpperCase();
            log.info("==========注册的服务：{}==========", service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (int i = 0; i < instances.size(); i ++) {
                ServiceInstance instance = instances.get(i);
                log.info("{}的第{}个实例的host：{}", service, i+1, instance.getHost());
                log.info("{}的第{}个实例的port：{}", service, i+1, instance.getPort());
                log.info("{}的第{}个实例的uri：{}", service, i+1, instance.getUri());
                log.info("{}的第{}个实例的serviceId：{}", service, i+1, instance.getServiceId());
                log.info("{}的第{}个实例的instanceId：{}", service, i+1, instance.getInstanceId());
            }
        }

    }

    @GetMapping("/testTimeout")
    public ResultVO<String> testTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultVO<>(port);
    }

    @GetMapping("/testGateway")
    public String testGateway() {
        return "########provider payment testGateway########, port: " + port;
    }
}

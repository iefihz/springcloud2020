package com.iefihz.springcloud.controller;

import com.iefihz.springcloud.conf.econf.ResultVO;
import com.iefihz.springcloud.entity.Payment;
import com.iefihz.springcloud.loadbalancer.RootLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;

/**
 * @author He Zhifei
 * @date 2020/7/7 14:10
 */
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RootLoadBalancer balancer;

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://SPRINGCLOUD-PAYMENT-SERVICE";     //通过application-name去调用其它微服务（可能有多个集群）

    @PostMapping("/payment")
    public ResultVO addPayment(@RequestBody @Validated Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, ResultVO.class);
    }

    @GetMapping("/payment/{id:[0-9]+}")
    public ResultVO getPayment(@PathVariable @Min(value = 1, message = "id最小为1") Long id) {
        ResultVO forObject = restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, ResultVO.class);
        return forObject;
    }

    @GetMapping("/payment4testBalancer/{id:[0-9]+}")
    public ResultVO getPayment4testBalancer(@PathVariable @Min(value = 1, message = "id最小为1") Long id) {
        ServiceInstance instance = balancer.instance("SPRINGCLOUD-PAYMENT-SERVICE");
        ResultVO result = restTemplate.getForObject(instance.getUri() + "/payment/" + id, ResultVO.class);
        return result;
    }

    @GetMapping("/testGateway")
    public String testGateway() {
        return "########consumer order testGateway########";
    }
}

package com.iefihz.springcloud.controller;

import com.iefihz.springcloud.conf.econf.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author He Zhifei
 * @date 2020/7/8 18:02
 */
@RestController
@RequestMapping("/orderZK")
public class OrderZKController {

    private static final String URL = "http://springcloud-payment-service-zk";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public ResultVO test() {
        return restTemplate.getForObject(URL + "/payment/test", ResultVO.class);
    }
}

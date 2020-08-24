package com.iefihz.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author He Zhifei
 * @date 2020/7/8 18:48
 */
@RestController
@RequestMapping("/paymentConsul")
public class PaymentConsulController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test() {
        return UUID.randomUUID().toString() + " port: " +port ;
    }
}

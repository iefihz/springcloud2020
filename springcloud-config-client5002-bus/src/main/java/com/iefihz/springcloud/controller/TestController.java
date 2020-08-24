package com.iefihz.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author He Zhifei
 * @date 2020/7/14 16:00
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/configInfo")
    public String configInfo() {
        return info;
    }
}

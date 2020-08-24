package com.iefihz.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author He Zhifei
 * @date 2020/7/14 14:40
 */
@RefreshScope
@RestController
@RequestMapping
public class TestController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/configInfo")
    public String configInfo() {
        return info;
    }
}

package com.iefihz.seata.controller;

import com.iefihz.seata.entity.Order;
import com.iefihz.seata.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author He Zhifei
 * @date 2020/8/19 10:18
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/product/{providerId}")
    public String add(@PathVariable("providerId") Long providerId) {
        Order order = new Order();
        order.setProviderId(providerId);
        order.setStatus(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderService.add(order);
        System.out.println("success");
        return "success";
    }
}

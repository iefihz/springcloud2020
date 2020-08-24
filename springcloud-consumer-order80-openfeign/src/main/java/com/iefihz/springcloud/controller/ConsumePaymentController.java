package com.iefihz.springcloud.controller;

import com.iefihz.springcloud.conf.econf.ResultVO;
import com.iefihz.springcloud.entity.Payment;
import com.iefihz.springcloud.openfeign.PaymentControllerOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author He Zhifei
 * @date 2020/7/11 21:53
 */
@RestController
@RequestMapping("/consumePayment")
public class ConsumePaymentController {

    @Autowired
    private PaymentControllerOpenFeign paymentControllerOpenFeign;

    @GetMapping("/{id:[0-9]+}")
    ResultVO<Payment> getById(@PathVariable Long id) {
        return paymentControllerOpenFeign.getById(id);
    }

    @PostMapping
    public ResultVO<Integer> add(@RequestBody @Validated Payment payment) {
        return paymentControllerOpenFeign.add(payment);
    }

    @GetMapping("/testTimeout")
    public ResultVO<String> testTimeout() {
        return paymentControllerOpenFeign.testTimeout();
    }
}

package com.iefihz.springcloud.service.impl;

import com.iefihz.springcloud.entity.Payment;
import com.iefihz.springcloud.dao.PaymentMapper;
import com.iefihz.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author He Zhifei
 * @date 2020/7/7 12:19
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int add(Payment payment) {
        return paymentMapper.add(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentMapper.getById(id);
    }

}

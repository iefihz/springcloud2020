package com.iefihz.springcloud.dao;

import com.iefihz.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author He Zhifei
 * @date 2020/7/7 12:22
 */
@Mapper
public interface PaymentMapper {
    int add(Payment payment);
    Payment getById(@Param("id") Long id);
}

package com.iefihz.seata.service.impl;

import com.iefihz.seata.dao.OrderMapper;
import com.iefihz.seata.entity.Order;
import com.iefihz.seata.feign.StorageFeign;
import com.iefihz.seata.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author He Zhifei
 * @date 2020/8/19 10:23
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeign storageFeign;

//    @GlobalTransactional(name = "add-Order", rollbackFor = Exception.class)
    @Override
    public void add(Order order) {
        orderMapper.add(order);

        // 减库存。。。
        storageFeign.decrease(order.getProviderId());
    }
}

package com.iefihz.seata.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author He Zhifei
 * @date 2020/8/19 10:15
 */
@Setter
@Getter
public class Order implements Serializable {
    private Long id;
    private Long providerId;
    private Integer status;     //0-失败；1-待支付；2-已付款
    private Date createTime;
    private Date updateTime;
}

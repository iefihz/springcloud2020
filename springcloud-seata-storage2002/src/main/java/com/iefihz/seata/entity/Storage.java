package com.iefihz.seata.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author He Zhifei
 * @date 2020/8/19 12:47
 */
@Setter
@Getter
public class Storage {
    private Long id;
    private Long num;
    private Date createTime;
    private Date updateTime;
}

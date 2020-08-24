package com.iefihz.seata.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author He Zhifei
 * @date 2020/8/19 12:53
 */
@Mapper
public interface StorageMapper {
    void decrease(Long providerId);
}

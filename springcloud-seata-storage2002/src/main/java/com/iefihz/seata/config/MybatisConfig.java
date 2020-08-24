package com.iefihz.seata.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author He Zhifei
 * @date 2020/8/19 11:48
 */
@Configuration
@MapperScan(basePackages = "com.iefihz.seata.dao")
public class MybatisConfig {
}

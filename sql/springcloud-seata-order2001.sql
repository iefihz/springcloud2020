CREATE TABLE `t_order` (
`id` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
`provider_id` BIGINT(20) NOT NULL COMMENT '产品ID',
`status` INT(1) NOT NULL DEFAULT 0 COMMENT '0-失败；1-待支付；2-已付款',
`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=INNODB DEFAULT CHARSET=UTF8
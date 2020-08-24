package com.iefihz.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author He Zhifei
 * @date 2020/7/12 1:52
 */
@Configuration
public class FeignConfig {

    /**
     * feign日志级别（再配置yml的logging日志打印级别）：
     *
     * NONE,
     * BASIC,
     * HEADERS,
     * FULL;
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

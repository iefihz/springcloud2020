package com.iefihz.springcloud.config;

import feign.Feign;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ConditionalOnClass({Feign.class})
public class FeignConfig {
    @Bean
    public WebMvcRegistrations feignWebRegistrations() {

//        springboot 1.x
//        return new WebMvcRegistrationsAdapter() {
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return new FeignRequestMappingHandlerMapping();
//            }
//        };

//        springboot 2.x
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    // 使用了Controller.class或者RequestMapping.class的注解都会被springmvc加载（默认），但使用了FeignClient.class注解的，不被springmvc加载（重写后）
    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }

//    若要对某个FeignClient进行禁用对Hystrix的支持，在Feign的配置加上这段被注释的代码即可
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }

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

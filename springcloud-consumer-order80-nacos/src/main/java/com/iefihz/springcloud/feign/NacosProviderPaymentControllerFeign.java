package com.iefihz.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(serviceId = "nacos-provider-payment")
@RequestMapping("/payment")
public interface NacosProviderPaymentControllerFeign {

    @GetMapping("/test")
    public String test();
}

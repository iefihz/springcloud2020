package com.iefihz.springcloud.openfeign;

import com.iefihz.springcloud.conf.econf.ResultVO;
import com.iefihz.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * <!--要添加这个依赖，不然@PathVariable的请求会自动把请求转为POST-->
 * <dependency>
 *     <groupId>io.github.openfeign</groupId>
 *     <artifactId>feign-httpclient</artifactId>
 *     <version>10.2.3</version>
 * </dependency>
 *
 * @author He Zhifei
 * @date 2020/7/11 21:51
 */
@RequestMapping("/payment")
@FeignClient(value = "SPRINGCLOUD-PAYMENT-SERVICE")
public interface PaymentControllerOpenFeign {

    @GetMapping("/{id:[0-9]+}")
    ResultVO<Payment> getById(@PathVariable Long id);

    @PostMapping
    ResultVO<Integer> add(@RequestBody @Validated Payment payment);

    @GetMapping("/testTimeout")
    ResultVO<String> testTimeout();
}

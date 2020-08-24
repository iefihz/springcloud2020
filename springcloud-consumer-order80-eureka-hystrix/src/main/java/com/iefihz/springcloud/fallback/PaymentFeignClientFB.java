package com.iefihz.springcloud.fallback;

import com.iefihz.springcloud.conf.econf.ResultVO;
import com.iefihz.springcloud.feign.PaymentFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author He Zhifei
 * @date 2020/7/13 15:30
 */
@Component
public class PaymentFeignClientFB implements PaymentFeignClient {
    @Override
    public ResultVO<String> ok(Integer id) {
        return new ResultVO<>("ok...fallback(对方服务器已宕机)");
    }
}

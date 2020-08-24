package com.iefihz.springcloud.controller;

import com.iefihz.springcloud.conf.econf.ResultVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author He Zhifei
 * @date 2020/7/13 13:07
 */
@Slf4j
@RestController
@RequestMapping("/paymentHystrix")
public class PaymentHystrixController {

    @GetMapping("/ok/{id}")
    public ResultVO<String> ok(@PathVariable("id") Integer id) {
        log.info("##############方法ok被调用！##############");
        return new ResultVO<>("方法ok被调用！当前线程：" + Thread.currentThread().getName() + ", 参数id：" + id);
    }

    /**
     * 使用JMeter进行压力测试，模拟200个线程组，每个循环100次去访问这个接口，此时再去访问ok的那个接口，发现，另外一个接口响应也开始变慢
     * @param id
     * @return
     */
    @GetMapping("/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id) {
        log.info("##############方法timeout被调用！##############");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "方法timeout被调用！当前线程：" + Thread.currentThread().getName() + ", 参数id：" + id;
    }

    /**
     * 服务降级：程序会直接报错或者执行需要5s，但只允许3s的执行时间，否则进行服务降级，执行timeoutOrErrorHandler()备选方案
     *
     * 注：一般服务降级用在消费端，但是服务端也是可以用的， 这里是作为服务端使用
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeoutOrErrorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/timeoutOrError/{id}")
    public String timeoutOrError(@PathVariable("id") Integer id) {
        log.info("##############方法timeoutOrError被调用！##############");
        boolean flag = new Random().nextInt(10) % 2 == 0 ? true : false;
        if (flag) {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            int j = 1/0;
        }
        return "方法timeoutOrError被调用！当前线程：" + Thread.currentThread().getName() + ", 参数id：" + id;
    }

    public String timeoutOrErrorHandler(Integer id) {
        return "系统繁忙，请稍后再试！当前线程：" + Thread.currentThread().getName() + ", 参数id：" + id;
    }


    /**
     * 测试服务熔断，这里仅仅在服务提供方测试，这里测试10s内失败率达到60%开启断路器，后续请求，先放一部分通过（half open）,
     * 成功率高了只会，再从half open -- > close       恢复全过程：断路器开启状态open --> half open --> close
     *
     * 所有配置项：com.netflix.hystrix.HystrixCommandProperties
     */
    @GetMapping("/circuitBreaker/{id}")
    @HystrixCommand(fallbackMethod = "testCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),      //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),     //请求总数阀值，默认20
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),       //快照时间窗：10s内，默认5s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),       //错误百分比阀值，默认50
    })
    public String testCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("id不能小于0");
        return "testCircuitBreaker succeed! id: " + id;
    }

    public String testCircuitBreakerFallback(Integer id) {
        return "testCircuitBreakerFallback !!!!!Fallback";
    }
}

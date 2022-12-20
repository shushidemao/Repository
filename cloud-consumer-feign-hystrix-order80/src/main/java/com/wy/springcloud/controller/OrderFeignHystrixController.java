package com.wy.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wy.springcloud.service.OrderFeignHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/order")
@DefaultProperties(defaultFallback = "defaultFallBack")
public class OrderFeignHystrixController {

    @Resource
    private OrderFeignHystrixService orderFeignHystrixService;
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/provider/paymentOk")
    public String paymentOk(@RequestParam Long id){
        log.info(orderFeignHystrixService.paymentOk(id));
        return orderFeignHystrixService.paymentOk(id);
    }
    @GetMapping(value = "/payment/provider/paymentTimeout")
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentTimeout(@RequestParam Long id){
        log.info("************************"+orderFeignHystrixService.paymentTimeout(id));
        return orderFeignHystrixService.paymentTimeout(id);
    }

    public String paymentTimeoutFallBack(Long id) {
        return "客户端线程池："+Thread.currentThread().getName()+"paymentTimeout:id="+id+"执行成功";
    }

    public String defaultFallBack() {
        return "客户端全局服务降级方法";
    }
}

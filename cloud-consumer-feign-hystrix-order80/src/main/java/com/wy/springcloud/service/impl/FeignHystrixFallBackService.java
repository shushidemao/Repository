package com.wy.springcloud.service.impl;

import com.wy.springcloud.service.OrderFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class FeignHystrixFallBackService implements OrderFeignHystrixService {
    @Override
    public String paymentOk(Long id) {
        return "paymentOk方法feign调用：服务降级实现！";
    }

    @Override
    public String paymentTimeout(Long id) {
        return "paymentTimeout方法feign调用：服务降级实现！";
    }
}

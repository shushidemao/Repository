package com.wy.springcloud.service;

import com.wy.springcloud.service.impl.FeignHystrixFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "cloud-provider-hystrix" ,fallback = FeignHystrixFallBackService.class)
public interface OrderFeignHystrixService {

    @GetMapping(value = "/payment/provider/paymentOk")
    public String paymentOk(@RequestParam("id") Long id);
    @GetMapping(value = "/payment/provider/paymentTimeout")
    public String paymentTimeout(@RequestParam("id") Long id);
}

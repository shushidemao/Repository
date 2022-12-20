package com.wy.springcloud.service;

import com.wy.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cloud-provider")
public interface FeignService {
    @GetMapping(value = "/payment/getAllById")
    Result getAllById(@RequestParam("id") Long id);
}

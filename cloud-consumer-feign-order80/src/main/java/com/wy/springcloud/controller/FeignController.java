package com.wy.springcloud.controller;

import com.wy.springcloud.entity.Result;
import com.wy.springcloud.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/feign")
public class FeignController {
    @Resource
    private FeignService feignService;

    @GetMapping(value = "/consumer/getAllById")
    public Result getAllById(@RequestParam Long id){
        val result = feignService.getAllById(id);
        return result;
    }
}

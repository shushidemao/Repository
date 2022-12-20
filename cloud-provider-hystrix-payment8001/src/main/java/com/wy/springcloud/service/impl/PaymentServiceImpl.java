package com.wy.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentOk(Long id) {
        return "线程池："+Thread.currentThread().getName()+"paymentOk:id="+id+"执行成功";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBack"
            ,commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")}
    )
    public String paymentTimeout(Long id) {
        int time = 2;
        //int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"paymentTimeout:id="+id+"执行失败";
    }

    public String paymentTimeoutFallBack(Long id) {
        return "线程池："+Thread.currentThread().getName()+"paymentTimeout:id="+id+"执行成功";
    }


    @Override
    @HystrixCommand(fallbackMethod = "circuitHystrix",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String circuit(Long id){
        if(id < 0){
            throw new RuntimeException("请输入正数***************");
        }
        String uuid = IdUtil.randomUUID();
        log.info("序列号="+uuid);
        return "执行成功，id="+id+"/n序列号="+uuid;
    }

    public String circuitHystrix(Long id){
        return "执行失败，id="+id;
    }
}

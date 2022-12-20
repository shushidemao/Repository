package com.wy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication()
@EnableEurekaClient
//切换ribbon负载均衡(默认轮询方式)，手写一个负载均衡轮询方式配置类(MyRibbonRule)去实现
//@RibbonClient(value = "CLOUD-PROVIDER", configuration = MyRibbonRule.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

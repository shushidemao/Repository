package com.wy.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    //ribbon负载均衡(默认轮询方式，切换负载均衡方式需注释该注解，且在启动类上加上@RibbonClient注解)
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

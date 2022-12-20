package com.wy.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRibbonRule {
    @Bean
    public IRule myRule(){
        //不同的轮询方式，创建不同的实例
        return new RandomRule();
    }
}

package com.wy.springcloud.LoadBalancer.impl;

import com.wy.springcloud.LoadBalancer.MylbRule;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MylbRuleimpl implements MylbRule {
    public AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndCrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current + 1;
            System.out.println("第"+next+"次调用");
        }while(!atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getAndCrement() % instances.size();
        ServiceInstance instance = instances.get(index);
        return instance;
    }
}

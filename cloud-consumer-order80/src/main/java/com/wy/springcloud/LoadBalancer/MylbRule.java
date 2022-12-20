package com.wy.springcloud.LoadBalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/*手写负载均衡实现*/
public interface MylbRule {
    ServiceInstance instance(List<ServiceInstance> instances);
}

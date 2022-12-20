package com.wy.springcloud.controller;

import com.wy.springcloud.LoadBalancer.MylbRule;
import com.wy.springcloud.entity.Payment;
import com.wy.springcloud.entity.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

;import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
@RequestMapping(value = "order")
public class OrderController {
    //单机版需要固定地址
    //public final String PAYMENT_URL="http://127.0.0.1:8001";
    //eureka集群整合ribbon后，可以直接通过eureka注册中心中的服务名调用，不再关心地址和端口号
    public final String PAYMENT_URL="http://CLOUD-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MylbRule mylbRule;

    @Autowired
    private DiscoveryClient discoveryClient;

    public AtomicInteger atomic = new AtomicInteger(0);

    @PostMapping(value = "/insert")
    public Result insert(@RequestBody Payment payment){
        val response = restTemplate.postForEntity(PAYMENT_URL + "/payment/insert", payment, Result.class);
        val result = response.getBody();
        return result;
    }

    @GetMapping(value = "/getAllById")
    public Result getAllById(@RequestParam Long id){
        val response = restTemplate.getForEntity(PAYMENT_URL + "/payment/getAllById?id="+id, Result.class);
        val result = response.getBody();
        if(response.getStatusCode().is2xxSuccessful()){
            log.info("查询结果："+result);
            return result;
        }else{
            return new Result(502,"查询失败");
        }
        //System.out.println("热部署测试");
    }

    /*
    **  ribbon之手写负载均衡策略中的轮询机制
    * */
    @GetMapping(value = "/consumer/getPort")
    public String getPort(){
        atomic.compareAndSet(atomic.get(),atomic.get()+1);
        System.out.println("全局实例atomic="+atomic.get());
        log.info("全局实例atomic="+atomic.get());
        //通过注册中心的查询服务实例
        val instances = discoveryClient.getInstances("CLOUD-PROVIDER");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        //调用手写负载均衡策略，获取其中一个服务实例
        val instance = mylbRule.instance(instances);
        //获取服务实例url
        val uri = instance.getUri();
        val instanceId = instance.getInstanceId();
        val serviceId = instance.getServiceId();
        //通过restTemplate远程调用服务
        val object = restTemplate.getForObject("http://"+serviceId + "/payment/getPort", String.class);
        return object;
    }
}

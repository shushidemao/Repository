package com.wy.springcloud.controller;

import com.wy.springcloud.entity.Payment;
import com.wy.springcloud.entity.Result;
import com.wy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/insert")
    public Result insertTable(@RequestBody Payment payment){
        val i = paymentService.insertTable(payment);
        log.info("插入结果："+i);
        if(i>0){
            return new Result<>().success("插入数据成功 serverPort="+serverPort,i);
        }else{
            return new Result<>().success("插入失败");
        }
    }

    @GetMapping(value = "/getAllById")
    public Result getAllById(@RequestParam Long id){
        val payment = paymentService.getAllById(id);
        log.info("查询结果："+payment);
        return new Result<>().success("查询数据成功 serverPort="+serverPort,payment);
    }

    @GetMapping("/getServiceInfo")
    public Object getServiceInfo(){
        val services = discoveryClient.getServices();
        for(String service:services){
            log.info("============== service:"+service);
        }
        val instances = discoveryClient.getInstances("CLOUD-PROVIDER");
        for(ServiceInstance instance: instances){
            val instanceId = instance.getInstanceId();
            val host = instance.getHost();
            val port = instance.getPort();
            val serviceId = instance.getServiceId();
            val uri = instance.getUri();
            log.info("instanceId:"+instanceId+"\thost:"+host+"\tport:"+port+"\tserviceId:"+serviceId+"\turi:"+uri);
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/getPort")
    public String getPort(){
        return serverPort;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dstr="2008-4-24";
        Date date=sdf.parse(dstr);
        System.out.println(date);
    }
}

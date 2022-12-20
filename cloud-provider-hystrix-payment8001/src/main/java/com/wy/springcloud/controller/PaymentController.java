package com.wy.springcloud.controller;

import com.wy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {

    @Resource
    public PaymentService paymentService;
    @Value("${server.port}")
    public String port;

    @GetMapping(value = "/provider/paymentOk")
    public String paymentOk(@RequestParam Long id) {
        log.info(paymentService.paymentOk(id));
        return paymentService.paymentOk(id);
    }
    @GetMapping(value = "/provider/paymentTimeout")
    public String paymentTimeout(@RequestParam Long id) {
        log.info(paymentService.paymentTimeout(id));
        return paymentService.paymentTimeout(id);
    }
    @PostMapping(value = "/shopOrder")
    public void shopOrder(@RequestParam("json") String json){
        String a = json;
        System.out.println(a);
    }

    @GetMapping(value = "/circuit")
    public String circuit(@RequestParam Long id){
        return paymentService.circuit(id);
    }
}

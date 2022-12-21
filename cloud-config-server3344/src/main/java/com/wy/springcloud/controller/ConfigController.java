package com.wy.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/config")
public class ConfigController {

    @Value("${server.port}")
    public String port;

    @GetMapping(value = "/getPort")
    public String getPort(){
        return port;
    }
}

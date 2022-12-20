package com.wy.springcloud.service;

public interface PaymentService {
    public String paymentOk(Long id);

    public String paymentTimeout(Long id);

    public String circuit(Long id);
}

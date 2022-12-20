package com.wy.springcloud.service;


import com.wy.springcloud.entity.Payment;

public interface PaymentService {
    public int insertTable(Payment payment);

    public Payment getAllById( Long id);
}

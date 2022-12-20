package com.wy.springcloud.service.impl;

import com.wy.springcloud.entity.Payment;
import com.wy.springcloud.dao.PaymentMapper;
import com.wy.springcloud.service.PaymentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int insertTable(Payment payment){
        val i = paymentMapper.insertTable(payment);
        return i;
    };
    @Override
    public Payment getAllById( Long id){
        val payment = paymentMapper.getAllById(id);
        return payment;
    };
}

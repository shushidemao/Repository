package com.wy.springcloud.dao;

import com.wy.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    public int insertTable(Payment payment);

    public Payment getAllById(@Param("id") Long id);

}

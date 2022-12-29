package com.wy.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer    code;
    private String     message;
    private T          data;

    public Result(Integer code,String message){
        this.code=code;
        this.message=message;
    }


    public  Result<T> success(T data){
        return new Result(200,"成功",data);
    }
    public  Result<T> success(String message,T data){
        return new Result(200,message,data);
    }
}

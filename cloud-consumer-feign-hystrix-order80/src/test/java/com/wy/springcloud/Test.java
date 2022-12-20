package com.wy.springcloud;

public class Test {
    public static void main(String[] args) {
        String invoiceNo="10331076.1";
        String no[] = invoiceNo.split("\\.");
        System.out.println(no[0]);
    }
}

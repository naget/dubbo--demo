package com.tf.demo.dubbo.api;

public interface CalculateService {
    ResultCode<Integer> sum(int a, int b);
}

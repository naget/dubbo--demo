package com.tf.demo.dubbo.impl;

import com.tf.demo.dubbo.api.CalculateService;
import com.tf.demo.dubbo.api.ResultCode;

public class CalculateServiceImpl implements CalculateService {

    public ResultCode<Integer> sum(int a, int b) {
        return new ResultCode<Integer>(a+b);
    }
}

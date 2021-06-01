package com.itsyw.service.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/1/4 9:35
 * @Version: 1.0
 * TODO: OrderServiceIMple3对应的Throwable处理的类
 */
@Slf4j
public class OrderServiceImpl3Fallback {

    // fallback
    /**
     * TODO: 要求：
     * 1. 当前方法的返回值和参数要跟原方法一致
     * 2. 当时允许再参数列表的最后加入一个参数Throwable，用来接收原方法中发生的异常
     * */
    public static String fallback(String name, Throwable e) {
        // TODO: 自定义异常处理逻辑
        log.error("触发了Throwable，内容为{}", e);
        return "Throwable";
    }

}

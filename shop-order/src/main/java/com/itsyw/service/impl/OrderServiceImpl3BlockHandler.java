package com.itsyw.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/1/4 9:32
 * @Version: 1.0
 * TODO: OrderServiceImpl3服务对应的BlockException处理的类
 */
@Slf4j
public class OrderServiceImpl3BlockHandler {

    // blockHandler
    /**
     * TODO: 要求：
     * 1. 当前方法的返回值和参数要跟原方法一致
     * 2. 当时允许再参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
     * */
    public static String blockHandler(String name, BlockException e) {
        // TODO: 自定义异常处理逻辑
        log.error("触发了BlockException，内容为{}", e);
        return "BlockException";
    }

}

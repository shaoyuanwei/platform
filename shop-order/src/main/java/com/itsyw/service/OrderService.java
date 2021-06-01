package com.itsyw.service;

import com.itsyw.domain.Order;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);
}

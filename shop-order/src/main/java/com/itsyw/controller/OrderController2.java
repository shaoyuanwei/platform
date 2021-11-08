package com.itsyw.controller;

import com.alibaba.fastjson.JSON;
import com.itsyw.domain.Order;
import com.itsyw.domain.Product;
import com.itsyw.service.OrderService;
import com.itsyw.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:44
 * @Version: 1.0
 * TODO:
 */
//@RestController
@Slf4j
public class OrderController2 {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;
    /**
     * 用户下单，订单创建
     * @return
     */
    @RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid){

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        // 调用商品微服务,查询商品信息
        Product product = productService.findByPId(pid);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("查询到{}号商品的信息,内容是{}", pid, JSON.toJSONString(product));

        // 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        // 为了防止垃圾数据
//        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }

    /**
     * 测试高并发
     * @return String
     */
    @RequestMapping("/order/message")
    public String message(){
        return "测试高并发";
    }

}

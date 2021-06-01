package com.itsyw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.itsyw.domain.Order;
import com.itsyw.domain.Product;
import com.itsyw.service.OrderService;
import com.itsyw.service.ProductService;
import com.itsyw.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:44
 * @Version: 1.0
 * TODO:
 */
//@RestController
@Slf4j
public class OrderController3 {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @RequestMapping("/order/message1")
    public String message1() {
        orderServiceImpl3.message("xx");
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
        orderServiceImpl3.message("xx");
        return "message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3") // 比须使用这个注解标识，热点规则不生效
    public String message3(String name, Integer age) {
        return name + age;
    }

    @RequestMapping("/order/message4")
    public String message4() {
        log.info("{}",applicationName);
        return orderServiceImpl3.message("xx");
    }

}

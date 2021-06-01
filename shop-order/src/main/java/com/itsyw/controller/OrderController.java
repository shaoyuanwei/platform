package com.itsyw.controller;

import com.alibaba.fastjson.JSON;
import com.itsyw.domain.Order;
import com.itsyw.domain.Product;
import com.itsyw.service.OrderService;
import com.itsyw.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:44
 * @Version: 1.0
 * TODO:
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 用户下单，订单创建5-- fegin
     * @return
     */
    @RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid){

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        // 调用商品微服务,查询商品信息
        Product product = productService.findByPId(pid);

        if (product.getPid() == -100) {
            Order order = new Order();
            order.setOid(-100L);
            order.setPname("下单失败");
            return order;
        }

        log.info("查询到{}号商品的信息,内容是{}", pid, JSON.toJSONString(product));

        // 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPanem());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }

    /**
     * 用户下单，订单创建4--ribbon实现负载均衡
     * 两步骤
     * 1. 在RestTemplate的生成方法上添加@LoadBalanced注解
     * 2. 修改服务调用方法，即使用服务名作为url地址，无需地址 + 端口 + 接口名称调用
     *    即 localhost:8081/product/1 ===> srvice-product/product/1
     * @return
     */
    /*@RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid){

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        // 调用商品微服务,查询商品信息
        *//**
         * 问题：
         * 1. 代码可读性差
         * 2. 编程风格不统一
         *//*
        Product product =
                restTemplate.getForObject("http://service-product/product/" + pid, Product.class);

        log.info("查询到{}号商品的信息,内容是{}", pid, JSON.toJSONString(product));

        // 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPanem());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }*/

/**
     * 用户下单，订单创建3--自定义（随机策略）负载均衡
     * @return
     */
    /*@RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid){

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        // 调用商品微服务,查询商品信息
        // serviceId 即服务名
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        // 随机选择商品服务实例
        // 根据instances长度判断调用哪个实例
        int index = new Random().nextInt(instances.size());
        ServiceInstance instance = instances.get(index);


        Product product =
                restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + pid, Product.class);

        log.info("查询到{}号商品的信息,内容是{}", pid, JSON.toJSONString(product));

        // 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPanem());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }*/

    /**
     * 用户下单，订单创建2
     * @return
     */
   /* @RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid){

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        // 调用商品微服务,查询商品信息
        // serviceId 即服务名
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);


        Product product =
                restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + pid, Product.class);

        log.info("查询到{}号商品的信息,内容是{}", pid, JSON.toJSONString(product));

        // 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPanem());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }*/

    /**
     * 用户下单，订单创建1
     * @return
     */
/*    @RequestMapping("/order/prod/{pid}")
    public Order createOrder(@PathVariable("pid") Integer pid){

        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        // 调用商品微服务,查询商品信息
        *//**
         * TODO:问题：
         * 1. 一旦服务提供者的地址信息发生变化，需要手动修改服务调用者的代码
         * 2. 一旦服务提供者做了集群，服务调用者一方无法实现负载均衡
         * 3. 一旦微服务变得越来越多，人工维护调用关系困难，如何来管理这个服务清单
         *//*
        Product product =
                restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);

        log.info("查询到{}号商品的信息,内容是{}", pid, JSON.toJSONString(product));

        // 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPanem());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }*/

}

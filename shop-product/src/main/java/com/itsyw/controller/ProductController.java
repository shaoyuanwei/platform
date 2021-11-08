package com.itsyw.controller;

import com.alibaba.fastjson.JSON;
import com.itsyw.domain.Product;
import com.itsyw.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:44
 * @Version: 1.0
 * TODO:
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/api1/demo1")
    public String demo1() {
        return "demo1";
    }

    @RequestMapping("/product/api1/demo2")
    public String demo2() {
        return "demo2";
    }

    @RequestMapping("/product/api2/demo1")
    public String demo3() {
        return "demo3";
    }

    @RequestMapping("/product/api2/demo2")
    public String demo4() {
        return "demo4";
    }

    /**
     * 根据pid获取商品信息
     *
     * @param pid 产品id
     * @return Product
     */
    @RequestMapping("/product/{pid}")
    public Product getOne(@PathVariable("pid") Integer pid) {

        log.info("接下来呀进行{}号商品信息查询", pid);

        Product product = productService.findByPId(pid);

        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(product));

        return product;

    }

    /**
     * 根据pid获取商品信息
     *
     * @return List<Product>
     */
    @RequestMapping("/product/list")
    public List<Product> list() {

        List<Product> list = productService.list();

        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(list));

        return list;

    }

}

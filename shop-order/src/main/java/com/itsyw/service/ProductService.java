package com.itsyw.service;

import com.itsyw.config.FeginConfig;
import com.itsyw.domain.Product;
//import com.itsyw.service.fallback.ProductServiceFallBack;
import com.itsyw.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/4 8:41
 * @Version: 1.0
 * TODO:
 */
// value用于声明指定调用得提供者得name
// fallback 用于指定当前fegin接口的容错类
//@FeginClient得value + @RequestMapping得value值 就是完成得请求地址 "http://service-product/product/{pid}"
@FeignClient(value = "service-product",
//        fallback = ProductServiceFallBack.class,
        configuration = FeginConfig.class,
        fallbackFactory = ProductServiceFallbackFactory.class)
public interface ProductService {

    /**
     * 指定请求得URI部分
     * @param pid 产品pid
     * @return Product
     */
    @RequestMapping("/product/{pid}")
    Product findByPId(@PathVariable("pid") Integer pid);

}

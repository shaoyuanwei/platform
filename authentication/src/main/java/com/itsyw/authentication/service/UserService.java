package com.itsyw.authentication.service;

import com.itsyw.authentication.service.fallback.UserServiceFallBack;
import com.itsyw.domain.User;
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
@FeignClient(value = "service-user",
        fallback = UserServiceFallBack.class)
public interface UserService {

    // @FeginClient得value + @RequestMapping得value值 就是完成得请求地址 "http://service-product/product/{pid}"
    // 指定请求得URI部分
    @RequestMapping("/product/{username}")
    User findByUsername(@PathVariable("username") String username);

}
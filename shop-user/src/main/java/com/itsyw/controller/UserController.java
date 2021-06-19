package com.itsyw.controller;

import com.alibaba.fastjson.JSON;
import com.itsyw.domain.Product;
import com.itsyw.domain.User;
import com.itsyw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:44
 * @Version: 1.0
 * TODO:
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据username获取用户信息
     *
     * @param username
     * @return
     */
    @RequestMapping("/info/{username}")
    public User getOne(@PathVariable("username") String username) {

        log.info("用户账号{}号信息查询", username);

        User user = userService.findByPUsername(username);

        log.info("用户查询成功，内容为{}", JSON.toJSONString(user));

        return user;


    }

}
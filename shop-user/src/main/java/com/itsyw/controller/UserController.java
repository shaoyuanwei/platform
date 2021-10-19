package com.itsyw.controller;

import com.alibaba.fastjson.JSON;
import com.itsyw.constant.HttpStatusCode;
import com.itsyw.domain.Product;
import com.itsyw.domain.User;
import com.itsyw.service.UserService;
import com.itsyw.utils.MD5Utils;
import com.itsyw.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/info")
    public User getOne(@RequestParam("username") String username) {

        log.info("用户账号{}号信息查询", username);

        User user = userService.findByPUsername(username);
        if (user != null) {
            user.setPassword(null);
        }
        log.info("用户查询成功，内容为{}", JSON.toJSONString(user));

        return user;

    }

    /**
     * 分页查询
     * @param pageNo
     * @return
     */
    @GetMapping("/list")
    public R listByPageNo(@RequestParam("pageNo") Integer pageNo) {

        Page<User> all = userService.findAll(pageNo);

        return R.ok().put("rows", all);
    }

    /**
     * 查询
     * @return
     */
    @GetMapping("/list/all")
    public R list() {

        List<User> all = userService.findAll();

        return R.ok().put("rows", all);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PutMapping("/register")
    public R register(User user) {

        log.warn("user is {}", user);
        if (user.getUsername() != null) {
            User userForData = userService.findByPUsername(user.getUsername());

            if (userForData == null) {
                log.info("user.register:{}", user.getUsername());
                user.setState(0);
                userService.save(user);
                return R.ok("user register is success");
            } else {
                log.info("user {} is exist", user.getUsername());
                return R.error(HttpStatusCode.HTTP_FAIL.getCode(), "user " +user.getUsername() + " is exist");
            }
        }
        return R.error("user is null");
    }


}
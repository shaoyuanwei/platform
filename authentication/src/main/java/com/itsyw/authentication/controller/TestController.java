package com.itsyw.authentication.controller;

import com.itsyw.authentication.service.UserService;
import com.itsyw.domain.User;
import com.itsyw.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/18 9:13
 * @Version: 1.0
 * TODO:
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/info/{username}")
    public R oauthInfo(@PathVariable("username") String username) {

        User user = userService.findByUsername(username);

        return R.ok().put("data", user);
    }

}

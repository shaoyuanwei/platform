package com.itsyw.authentication.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/16 8:14
 * @Version: 1.0
 * TODO: 登录Controller
 */
@RestController
public class LoginController {

    @GetMapping("/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception = (AuthenticationException) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        try {
            response.getWriter().write(exception.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

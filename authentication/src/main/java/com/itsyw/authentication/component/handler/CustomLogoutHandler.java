package com.itsyw.authentication.component.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/16 16:56
 * @Version: 1.0
 * TODO: logoutHandler
 */
@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        try {
            httpServletRequest.getParameter("url");
            httpServletResponse.sendRedirect("http://localhost:8080");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

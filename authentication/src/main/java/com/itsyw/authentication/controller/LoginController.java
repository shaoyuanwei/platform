package com.itsyw.authentication.controller;

import com.itsyw.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class LoginController {

    /**
     * 把当前的请求缓存到 session 里去
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 重定向 策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    public R requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到请求对象
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            //获取 跳转url
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:"+targetUrl);

            //判断 targetUrl 是不是 .html　结尾, 如果是：跳转到登录页(返回view)
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                String redirectUrl = "http://localhost:8080/";
                redirectStrategy.sendRedirect(request,response,redirectUrl);
            }
        }
        //如果不是，返回一个json 字符串
        return R.error("访问的服务需要身份认证，请引导用户到登录页");
    }

    @DeleteMapping("/logout")
    public R logout(@RequestParam("accessToken") String accessToken) {
        if (consumerTokenServices.revokeToken(accessToken)) {
            return R.ok("logout success");
        } else {
            return R.error("logout failure");
        }
    }

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

package com.itsyw.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/8 14:14
 * @Version: 1.0
 * TODO: Fegin中传递token解决配置
 */
@Configuration
public class FeginConfig implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 添加token
        requestTemplate.header("Authorization", request.getHeader("Authorization"));
    }
}

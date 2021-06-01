package com.itsyw.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/10 9:17
 * @Version: 1.0
 * TODO: 实例化CommonFilter 链路流控模式
 */
//@Configuration
public class FilterContextConfig {

    @Bean
    public FilterRegistrationBean setFilterFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        // 入口资源关闭聚合
        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        registration.setName("sentinelFiter");
        registration.setOrder(1);
        return registration;
    }

}

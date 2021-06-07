package com.itsyw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import javax.sql.DataSource;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/7 16:57
 * @Version: 1.0
 * TODO: 网管层安全配置
 */
@Configuration
public class SecurityConfiguration {

    private static final String MAX_AGE = "18000L";

    @Autowired
    private DataSource dataSource;

    /**
     * 跨域配置
     */
    public WebFilter corsFilter() {
        return null;
    }

}

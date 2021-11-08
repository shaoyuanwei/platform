package com.itsyw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 16:34
 * @Version: 1.0
 * TODO:
 */
@Configuration
public class TokenConfig {

//    private static final String VERIFY_KEY = "Shop_PKQ";

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }


}

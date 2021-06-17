package com.itsyw.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 16:16
 * @Version: 1.0
 * TODO: TOKEN配置
 */
@Configuration
public class TokenConfig {

    private static final String SIGN_KEY = "Shop_PKQ";

    /**
     * jwt token 存储
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore1() {
        return new JwtTokenStore(jwtAccessTokenConverter1());
    }

    /**
     * jwt tokenConvert
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter1() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGN_KEY);
        return jwtAccessTokenConverter;
    }

}

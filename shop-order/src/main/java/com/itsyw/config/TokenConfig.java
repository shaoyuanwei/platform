package com.itsyw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 16:34
 * @Version: 1.0
 * TODO:
 */
@Configuration
public class TokenConfig {

    private static final String VERIFY_KEY = "Shop_PKQ";

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(VERIFY_KEY);
        jwtAccessTokenConverter.setSigningKey(VERIFY_KEY);
        return jwtAccessTokenConverter;
    }


}

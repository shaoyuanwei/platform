package com.itsyw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 13:24
 * @Version: 1.0
 * TODO: 安全配置
 */
@EnableWebFluxSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain webFluxFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/**").permitAll()
                // options 请求默认放行
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                .formLogin();

        return http.build();
    }

}

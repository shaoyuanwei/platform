package com.itsyw.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 16:16
 * @Version: 1.0
 * TODO: TOKEN配置
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.itsyw.domain"})
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}

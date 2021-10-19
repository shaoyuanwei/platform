package com.itsyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:37
 * @Version: 1.0
 * TODO:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class);
    }

}

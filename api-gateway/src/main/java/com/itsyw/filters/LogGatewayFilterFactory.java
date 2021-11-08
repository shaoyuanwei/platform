package com.itsyw.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/3/12 13:43
 * @Version: 1.0
 * TODO: 自定义的局部过滤器
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    /**
     * 构造函数
     */
    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    /**
     *  读取配置文件中的参数， 赋值到配置类中
     * @return  List<String>
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    /**
     * 过滤器逻辑
     * @param config gateway配置
     * @return GatewayFilter
     */
    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            if (config.isCacheLog()) {
                System.out.println("cacheLog已经开启了");
            }
            if (config.isConsoleLog()) {
                System.out.println("consoleLog已经开启了");
            }

            return chain.filter(exchange);
        };
    }

    /**
     * 配置类 接收配置参数
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;
    }

}

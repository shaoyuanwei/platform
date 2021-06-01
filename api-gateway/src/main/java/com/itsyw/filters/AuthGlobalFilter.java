package com.itsyw.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/3/12 14:49
 * @Version: 1.0
 * TODO: 自定义全局过滤器，作用是统一鉴权
 * 要求：比须实现两个接口，并且实现里面的两个方法
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    // 过滤器逻辑卷
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 统一鉴权逻辑
        String token = exchange.getRequest().getQueryParams().getFirst("token");

//        if (!StringUtils.equals("admin", token)) {
//             认证失败
//            log.info("认证失败了");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }

        // 放行
        return chain.filter(exchange);
    }

    // 用来标识当前过滤器的优先级，返回值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}

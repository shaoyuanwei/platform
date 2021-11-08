package com.itsyw.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BeforeRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/3/12 8:46
 * @Version: 1.0
 * TODO: 这是一个自定义的路由断言工厂类
 * 要求：
 * 1. 名字比须是 配置+RoutePredicateFactory
 * 2. 必须继承AbstractRoutePredicateFactory<配置类>
 */
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    /**
     * 构造函数
     */
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件中的参数值，给他赋值到配置类中的属性上
     *
     * @return List<String>
     */
    @Override
    public List<String> shortcutFieldOrder() {
        // 这个位置的顺序比须跟配置文件中的值的顺序对应
        return Arrays.asList("minAge", "maxAge");
    }

    // 断言逻辑

    /**
     * 断言逻辑
     * @param config 年龄断言工厂类配置
     * @return Predicate<ServerWebExchange>
     */
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            // 接收前台传入的age参数
            String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
            // 1. 先判断是否为空
            if (StringUtils.isNotEmpty(ageStr)) {
                int age = Integer.parseInt(ageStr);
                // 2. 如果不为空再进行路由逻辑判断
                return age < config.getMaxAge() && age > config.getMinAge();
            }
            return false;
        };
    }

    /**
     * 配置类，用于接收配置文件中的对应参数
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;
        private int maxAge;
    }
}

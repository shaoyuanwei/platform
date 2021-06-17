package com.itsyw.authentication.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/7 16:23
 * @Version: 1.0
 * TODO: 用户认证
 */
@Slf4j
public class ReactiveJdbcAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return null;
    }
}
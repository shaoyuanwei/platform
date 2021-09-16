package com.itsyw.authentication.component.linstener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/14 15:57
 * @Version: 1.0
 * TODO: 用户登录失败监听器
 */
@Component
@Slf4j
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        log.info("event.exception:{}", event.getException());
        log.error("----------失败--------------");
    }
}

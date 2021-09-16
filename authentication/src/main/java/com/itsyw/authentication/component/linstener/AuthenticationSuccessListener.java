package com.itsyw.authentication.component.linstener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/14 15:55
 * @Version: 1.0
 * TODO: 用户登录成功监听器
 */
@Component
@Slf4j
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // 用户通过输入用户名和密码登录成功
        Object principal = event.getAuthentication().getPrincipal();
        log.info("---AuthenticationSuccessEvent---:{}", principal.toString());
    }

}

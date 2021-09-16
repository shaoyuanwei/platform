package com.itsyw.authentication.component.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/15 9:29
 * @Version: 1.0
 * TODO: 用户自定义身份认证
 */
@Slf4j
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        setUserDetailsService(userDetailsService);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        super.additionalAuthenticationChecks(userDetails, authentication);

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(
                    messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        //我就改了这个地方，增加一个验证码登录标识(具体怎么做就看自己了)
        // 【原本的是登录密码和数据密码不等就抛出异常，我用验证码登录时压根都不知道密码(ー`´ー)】
        //so 我给短信登录时赋值一个默认密码（验证码登录标识）来判断，不让这儿报异常
//        if (!presentedPassword.equals("YZMLG_KSssdS1D145Sd4S")) {
        if (!getPasswordEncoder().matches(presentedPassword, "{bcrypt}" + userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
//            publisher.publishEvent(new UserLoginFailedEvent(authentication));
            throw new BadCredentialsException(messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
//        }

    }

}

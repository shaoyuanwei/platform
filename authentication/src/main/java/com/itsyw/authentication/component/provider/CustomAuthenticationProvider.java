package com.itsyw.authentication.component.provider;

import com.itsyw.authentication.component.factory.CustomPasswordEncoderFactories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
//        setPasswordEncoder(new BCryptPasswordEncoder());
        setPasswordEncoder(CustomPasswordEncoderFactories.createDelegatingPasswordEncoder());
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
//        if (!presentedPassword.equals("YZMLG_KSssdS1D145Sd4S")) {
        if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
//        if (!getPasswordEncoder().matches(presentedPassword, "{bcrypt}" + userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
//            publisher.publishEvent(new UserLoginFailedEvent(authentication));
            throw new BadCredentialsException(messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "password is failure"));
//                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
//        }

    }


}

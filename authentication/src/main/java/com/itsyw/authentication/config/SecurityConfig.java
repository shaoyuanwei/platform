package com.itsyw.authentication.config;

import com.itsyw.authentication.component.handler.CustomAuthenticationFailureHandler;
import com.itsyw.authentication.component.handler.CustomAuthenticationSuccessHandler;
import com.itsyw.authentication.component.provider.CustomAuthenticationProvider;
import com.itsyw.authentication.component.provider.UsernameAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 16:03
 * @Version: 1.0
 * TODO:
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService);
    }

    @Bean
    public AuthenticationProvider usernameAuthenticationProvider() {
        return new UsernameAuthenticationProvider(userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        log.error("userDetailsService:{}", userDetailsService);
        auth.authenticationProvider(authenticationProvider());
//                .authenticationProvider(usernameAuthenticationProvider());
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//        auth.parentAuthenticationManager(authenticationManagerBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // ????????????????????????????????????
                .csrf().disable()
                // ???????????????
                .authorizeRequests()
                // ???????????????????????????????????????????????????????????????????????????
                .antMatchers("/oauth/**", "/userLogout").permitAll()
                // ????????????
                .anyRequest()
                // ???????????????????????????????????????
                .authenticated()
                .and()
                // ????????????
                .formLogin()
                // ??????????????????
                .loginPage("http://localhost:8080/")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/userLogout")
                .logoutSuccessUrl("/authentication/require").permitAll();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/oauth/**", "/info/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("http://localhost:8080")
//                .successForwardUrl("/login/success")
//                .failureForwardUrl("/login/error")
//                .and().logout()
//                .permitAll();
//                .usernameParameter("username")
//                .passwordParameter(new BCryptPasswordEncoder().encode("password"))
//                .loginPage("/authentication/login")
//                .failureUrl("/authentication/login?failed")
//                .loginProcessingUrl("/authentication/login/process");
    }

}

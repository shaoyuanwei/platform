package com.itsyw.authentication.config;

import com.alibaba.fastjson.JSONObject;
import com.itsyw.authentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter(new BCryptPasswordEncoder().encode("password"))
                .loginPage("/authentication/login")
                .failureUrl("/authentication/login?failed")
                .loginProcessingUrl("/authentication/login/process");
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // 内存模式下创建的用户

        com.itsyw.domain.User user = userService.findByUsername("admin");

        log.info("user：{}", JSONObject.toJSONString(user));

        // 权限列表
        UserDetails admin = User.withUsername("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                // 角色
                .roles("ADMIN", "DEVELOPER")
                // 权限
                .authorities("get", "update")
                .build();
        // 开发者
        UserDetails developer = User.withUsername("developer")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("DEVELOPER")
                .authorities("get")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(developer);
        return userDetailsManager;

    }



}

package com.itsyw.authentication.config;

import com.itsyw.authentication.component.filter.LoginFilter;
import com.itsyw.authentication.component.provider.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        return loginFilter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.error("userDetailsService:{}", userDetailsService);
        auth.authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        auth.parentAuthenticationManager(authenticationManagerBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/info/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("http://localhost:8080")
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/error")
                .and().logout()
                .permitAll();
//                .and().addFilter(loginFilter());
//                .usernameParameter("username")
//                .passwordParameter(new BCryptPasswordEncoder().encode("password"))
//                .loginPage("/authentication/login")
//                .failureUrl("/authentication/login?failed")
//                .loginProcessingUrl("/authentication/login/process");
    }
/*
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // 内存模式下创建的用户
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

    }*/

}

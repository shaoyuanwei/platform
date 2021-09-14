//package com.itsyw.authentication.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
//import java.util.Collections;
//
///**
// * @Author: YuanWei Shao
// * @Date: 2021/6/11 13:32
// * @Version: 1.0
// * TODO: 客户端信息存于内存中
// */
//@Configuration
//@EnableAuthorizationServer
//public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//
//    @Autowired
//    private TokenStore tokenStore1;
//
//    @Autowired
//    private JwtAccessTokenConverter jwtAccessTokenConverter1;
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//
//        endpoints
//                // 允许token请求调用的请求类型
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
//                // 认证管理器
//                .authenticationManager(authenticationManager)
//                // token
//                .tokenServices(tokenServices())
//                // 认证码
//                .authorizationCodeServices(authorizationCodeServices());
//
//    }
//
//    @Bean
//    public AuthorizationServerTokenServices tokenServices() {
//
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        // 认证管理器
//        tokenServices.setAuthenticationManager(authenticationManager);
//        // 客户端信息
//        tokenServices.setClientDetailsService(clientDetailsService);
//        // jwt token 存储
//        tokenServices.setTokenStore(tokenStore1);
//        // token 增强
//        TokenEnhancerChain tokenEnhancer = new TokenEnhancerChain();
//        tokenEnhancer.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter1));
//        tokenServices.setTokenEnhancer(tokenEnhancer);
//        // refresh_token 允许支持书新token
//        tokenServices.setSupportRefreshToken(true);
//        // token 有效期2小时
//        tokenServices.setAccessTokenValiditySeconds(2 * 60 * 60);
//        // 刷新token有效期 7天
//        tokenServices.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
//        tokenServices.setReuseRefreshToken(true);
//
//        return tokenServices;
//
//    }
//
//    /**
//     * 授权码
//     *
//     * @return AuhorizatiooonCodeServices
//     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new InMemoryAuthorizationCodeServices();
//    }
//
//    /**
//     * 自定义 加密器
//     *
//     * @return PasswordEncoder
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // 配置内存中的client
//        clients.inMemory()
//                .withClient("admin")
//                .secret(new BCryptPasswordEncoder().encode("Shop_PKQ"))
//                // 客户端允许的授权模式
//                .authorities("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                // 允许的授权范围
//                .scopes("all", "write", "read")
//                // 验证回调地址
//                .redirectUris("https://www.baidu.com")
//                .and()
//                .withClient("user")
//                .secret(new BCryptPasswordEncoder().encode("Shop_PKQ"))
//                // 客户端允许的授权模式
//                .authorities("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                // 允许的授权范围
//                .scopes("all", "write", "read")
//                // 验证回调地址
//                .redirectUris("https://www.baidu.com")
//                .and()
//                .build();
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                // oauth/token_key是公开的
//                .tokenKeyAccess("permitAll()")
//                // oauth/check_token公开
//                .checkTokenAccess("permitAll()")
//                // 表单认证 (申请令牌)
//                .allowFormAuthenticationForClients();
//    }
//}

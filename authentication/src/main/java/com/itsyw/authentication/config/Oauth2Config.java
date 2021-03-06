package com.itsyw.authentication.config;

import com.itsyw.authentication.component.JdbcTokenStoreUserApprovalHandler;
import com.itsyw.authentication.component.factory.CustomPasswordEncoderFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/9 16:09
 * @Version: 1.0
 * TODO:
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Resource(name = "userDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // ???????????????oauth_client_details??????????????????
//        clients.withClientDetails(clientDetailsService())
//                .jdbc()
//                .passwordEncoder(CustomPasswordEncoderFactories.createDelegatingPasswordEncoder());
//                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        clients.jdbc(dataSource).passwordEncoder(CustomPasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jdbcTokenStore()).authenticationManager(authenticationManager)
                // ?????????AccessToken
//                .accessTokenConverter(accessTokenConverter)
                // ??????userDetailsService
                .userDetailsService(userDetailsService)
                // ?????????????????????
                .authorizationCodeServices(authorizationCodeServices())
                // ??????userApprovalHandler
                .userApprovalHandler(userApprovalHandler())
                // ??????tokenServices
//                .tokenServices(tokenServices())
                // ??????token???????????????
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // ??????token
                .reuseRefreshTokens(true);
    }

    @RequestMapping(value = "/oauth/logout", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = jdbcTokenStore().readAccessToken(tokenValue);
            jdbcTokenStore().removeAccessToken(accessToken);
        }
    }

    /**
     * ??????????????????????????????
     *
     * @param security ????????????
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // ??????/oauth/token_key??????????????????????????????
                .tokenKeyAccess("isAuthenticated()")
                // ??????/oauth/check_token??????????????????????????????
                .checkTokenAccess("isAuthenticated()")
                // ?????????????????? ??????????????????????????????????????????code??????token
                .allowFormAuthenticationForClients();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        Assert.state(dataSource != null, "DataSource must be provided");
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public TokenStore jdbcTokenStore() {
        Assert.state(dataSource != null, "DataSource must be provided");
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        Assert.state(dataSource != null, "DataSource must be provided");
        //        service.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService());
    }

    @Bean
    public UserApprovalHandler userApprovalHandler() {
        JdbcTokenStoreUserApprovalHandler approvalHandler = new JdbcTokenStoreUserApprovalHandler();
        approvalHandler.setTokenStore(jdbcTokenStore());
        approvalHandler.setRequestFactory(oAuth2RequestFactory());
        return approvalHandler;
    }

    @Bean
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService());
        services.setSupportRefreshToken(true);
        services.setTokenStore(jdbcTokenStore());
        return services;
    }

}

package com.itsyw.authentication.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/10 9:37
 * @Version: 1.0
 * TODO:
 */
@Component("jdbcAccessTokenConverter")
@Slf4j
public class JdbcAccessTokenConverter implements AccessTokenConverter{

    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    public void setAccessTokenConverter(AccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }

    public AccessTokenConverter getAccessTokenConverter() {
        return tokenConverter;
    }

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        oAuth2AccessToken = this.enhance(oAuth2AccessToken, oAuth2Authentication);
        log.info(oAuth2AccessToken.getValue());
        return tokenConverter.convertAccessToken(oAuth2AccessToken, oAuth2Authentication);
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String s, Map<String, ?> map) {
        return tokenConverter.extractAccessToken(s, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return tokenConverter.extractAuthentication(map);
    }

    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        String grantType = authentication.getOAuth2Request().getGrantType();
        // 授权码模式和密码模式才自定义token信息
        if ("authorization_code".equals(grantType) || "password".equals(grantType) || "refresh_token".equals(grantType)) {
            String userName = authentication.getUserAuthentication().getName();
            // 自定义一些token信息
            Map<String, Object> additionalInformation = new LinkedHashMap<>(16);
            additionalInformation.put("user_name", userName);
            additionalInformation = Collections.unmodifiableMap(additionalInformation);
            result.setAdditionalInformation(additionalInformation);
        }
        return result;
    }

}

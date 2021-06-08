package com.itsyw.authentication.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import reactor.core.publisher.Mono;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/7 16:23
 * @Version: 1.0
 * TODO: 用户认证
 */
@Slf4j
public class ReactiveJdbcAuthenticationManager implements ReactiveAuthenticationManager {

    private TokenStore tokenStore;

    public ReactiveJdbcAuthenticationManager(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessToken -> {
                    log.info("access_token:{}", accessToken);
                    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                    if (oAuth2AccessToken == null) {
                        return Mono.error(new InvalidTokenException("invalid access token, please check"));
                    }
                    if (oAuth2AccessToken.isExpired()) {
                        return Mono.error(new InvalidTokenException("access token has expired, please reacquire token"));
                    }
                    OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(accessToken);
                    if (oAuth2Authentication == null) {
                        return Mono.error(new InvalidTokenException(" Access Token 无效"));
                    }

                    return Mono.just(oAuth2Authentication);

                }))
                .cast(Authentication.class);
    }
}
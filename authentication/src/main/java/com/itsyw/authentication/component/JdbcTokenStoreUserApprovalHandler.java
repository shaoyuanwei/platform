package com.itsyw.authentication.component;

import com.itsyw.authentication.service.OauthService;
import com.itsyw.domain.OauthClientDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/9 16:30
 * @Version: 1.0
 * TODO: 自定义TokenStoreUserApprovalHandler
 */
@Slf4j
public class JdbcTokenStoreUserApprovalHandler extends TokenStoreUserApprovalHandler {

    @Autowired
    private OauthService oauthService;

    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {

        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }

        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        String clientId = authorizationRequest.getClientId();
        log.info("clientId:{}", clientId);
        OauthClientDetails clientDetails = oauthService.loadOauthClientDeatils(clientId);
        return clientDetails != null;

    }
}

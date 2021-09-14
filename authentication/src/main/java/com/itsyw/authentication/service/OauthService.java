package com.itsyw.authentication.service;

import com.itsyw.domain.OauthClientDetails;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/9 16:38
 * @Version: 1.0
 * TODO:
 */
public interface OauthService {

    OauthClientDetails loadOauthClientDeatils(String clientId);

}

package com.itsyw.authentication.service.impl;

import com.itsyw.authentication.dao.OauthDao;
import com.itsyw.authentication.service.OauthService;
import com.itsyw.domain.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/9 16:37
 * @Version: 1.0
 * TODO:
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private OauthDao oauthDao;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public OauthClientDetails loadOauthClientDeatils(String clientId) {

        OauthClientDetails details = new OauthClientDetails();
        details.setClientId(clientId);
        Example<OauthClientDetails> e = Example.of(details);
        Optional<OauthClientDetails> one = oauthDao.findOne(e);

        return one.orElse(null);

    }
}

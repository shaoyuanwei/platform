package com.itsyw.authentication.dao;

import com.itsyw.domain.OauthClientDetails;
import com.itsyw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/9 16:42
 * @Version: 1.0
 * TODO:
 */
public interface OauthDao extends JpaRepository<OauthClientDetails, String> {
}

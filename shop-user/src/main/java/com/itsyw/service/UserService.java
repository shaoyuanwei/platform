package com.itsyw.service;

import com.itsyw.domain.Product;
import com.itsyw.domain.User;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByPUsername(String username);

    User save(User user);

}

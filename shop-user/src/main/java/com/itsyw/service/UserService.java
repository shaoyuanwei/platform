package com.itsyw.service;

import com.itsyw.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

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

    Page<User> findAll(Integer pageNo);

    List<User> findAll();

}

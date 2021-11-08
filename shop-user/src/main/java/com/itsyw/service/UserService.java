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
     * @param username 用户名
     * @return User
     */
    User findByPUsername(String username);

    /**
     * 添加用户信息
     * @param user 用心信息
     * @return User
     */
    User save(User user);

    /**
     * 用户分页查询
     * @param pageNo 页数
     * @return Page<User>
     */
    Page<User> findAll(Integer pageNo);

    /**
     * 查所有用户
     * @return List<User>
     */
    List<User> findAll();

}

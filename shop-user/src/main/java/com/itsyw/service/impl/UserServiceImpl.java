package com.itsyw.service.impl;

import com.itsyw.dao.UserDao;
import com.itsyw.domain.Product;
import com.itsyw.domain.User;
import com.itsyw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByPUsername(String username) {
        User user = new User();
        user.setUsername(username);
        Example<User> e = Example.of(user);
        Optional<User> one = userDao.findOne(e);
        return one.get();
    }
}

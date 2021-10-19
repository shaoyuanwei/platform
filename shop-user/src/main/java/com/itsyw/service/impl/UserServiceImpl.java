package com.itsyw.service.impl;

import com.itsyw.dao.UserDao;
import com.itsyw.domain.User;
import com.itsyw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return one.orElse(null);
    }

    @Override
    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public Page<User> findAll(Integer pageNo) {
        PageRequest page = PageRequest.of(pageNo - 1, 5);
        return userDao.findAll(page);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}

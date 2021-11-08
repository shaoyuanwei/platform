package com.itsyw.authentication.service.impl;

import com.itsyw.authentication.dao.UserDao;
import com.itsyw.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/9 14:04
 * @Version: 1.0
 * TODO:
 */
@Service("userDetailService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class, readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u = new User();
        u.setUsername(username);
        Example<User> e = Example.of(u);
        Optional<User> one = userDao.findOne(e);

        User user = one.orElse(null);
        if (user == null) {
            log.info("登录用户[{}]没注册!", username);
            throw new UsernameNotFoundException("登录用户[" + username + "]没注册!");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        String role = user.getRole();
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}

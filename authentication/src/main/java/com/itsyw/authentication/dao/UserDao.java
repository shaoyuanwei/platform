package com.itsyw.authentication.dao;

import com.itsyw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
public interface UserDao extends JpaRepository<User, Integer> {
}

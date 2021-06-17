package com.itsyw.authentication.service.fallback;

import com.itsyw.authentication.service.UserService;
import com.itsyw.domain.Product;
import com.itsyw.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/3/10 14:03
 * @Version: 1.0
 * TODO: 这是一个容错类，需要实现Fegin所在的接口，并去实现接口中的所有方法
 * 一旦Fegin远程调用出现问题，就会进入当前类中同名的方法中，执行容错逻辑
 */
@Service
public class UserServiceFallBack implements UserService {

    @Override
    public User findByUsername(String username) {

        // 容错逻辑
        User user = new User();
        user.setUid(-100);
        user.setUsername("远程调用用户微服务出现异常了，进入了容错逻辑");

        return user;
    }
}

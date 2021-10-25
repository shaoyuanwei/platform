package com.itsyw.service.impl;

import com.itsyw.dao.RoleDao;
import com.itsyw.domain.shop.ShopRole;
import com.itsyw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/21 10:46
 * @Version: 1.0
 * TODO:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ShopRole save(ShopRole role) {
        if (role.getId() == null) {
            ShopRole r = new ShopRole();
            r.setRoleName(role.getRoleName());
            Example<ShopRole> e = Example.of(r);
            Optional<ShopRole> one = roleDao.findOne(e);
            if (one.isPresent()) {
                return null;
            }
            role.setCreateTime(new Date());
        }
        role.setUpdateTime(new Date());
        return roleDao.save(role);
    }

    @Override
    public List<ShopRole> find() {
        return roleDao.findAll();
    }
}

package com.itsyw.service;

import com.itsyw.domain.shop.ShopRole;

import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/21 10:45
 * @Version: 1.0
 * TODO:
 */
public interface RoleService {

    ShopRole save(ShopRole role);

    List<ShopRole> find();
}

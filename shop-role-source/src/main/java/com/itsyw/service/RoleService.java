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

    /**
     * 保存角色信息
     * @param role 角色信息
     * @return ShopRole
     */
    ShopRole save(ShopRole role);

    /**
     * 查找所有角色
     * @return List<ShopRole>
     */
    List<ShopRole> find();
}

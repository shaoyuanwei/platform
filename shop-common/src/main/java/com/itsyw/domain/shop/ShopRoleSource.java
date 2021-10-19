package com.itsyw.domain.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 15:13
 * @Version: 1.0
 * TODO:
 */
@Entity(name = "shop_role_source")
@Data
public class ShopRoleSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    private Integer sourceId;

}

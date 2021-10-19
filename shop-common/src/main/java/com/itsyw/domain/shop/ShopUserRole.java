package com.itsyw.domain.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 15:15
 * @Version: 1.0
 * TODO:
 */
@Entity(name = "shop_user_role")
@Data
public class ShopUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer sourceId;

}

package com.itsyw.domain.shop;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 15:11
 * @Version: 1.0
 * TODO:
 */
@Entity(name = "shop_role")
@Data
public class ShopRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    private String remark;

    private Integer createUserId;

    private Date createTime;

    private Date updateTime;

}

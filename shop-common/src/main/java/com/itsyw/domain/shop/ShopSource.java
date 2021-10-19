package com.itsyw.domain.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 15:01
 * @Version: 1.0
 * TODO: 资源表-菜单表
 */
@Entity(name = "shop_source")
@Data
public class ShopSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer parentId;

    private String source;

    private String url;

    private Integer type;

    private String icon;

    private Date createdTime;

    private Date updateTime;

}

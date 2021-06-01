package com.itsyw.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 11:20
 * @Version: 1.0
 * TODO:
 */
@Entity(name = "shop_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    private Integer uid;

    private String username;

    private Integer pid;

    private String pname;

    private Double pprice;

    private Integer number;

}

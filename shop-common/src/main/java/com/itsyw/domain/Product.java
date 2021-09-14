package com.itsyw.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 11:19
 * @Version: 1.0
 * TODO:
 */
@Data
@Entity(name = "shop_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    private String pname;

    private Double pprice;

    private Integer stock;

}

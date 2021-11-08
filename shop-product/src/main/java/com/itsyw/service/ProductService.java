package com.itsyw.service;

import com.itsyw.domain.Product;

import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
public interface ProductService {

    /**
     * 根据pid查询商品信息
     * @param pid 产品id
     * @return Product
     */
    Product findByPId(Integer pid);

    /**
     * 商品列表
     * @return List<Product>
     */
    List<Product> list();

}

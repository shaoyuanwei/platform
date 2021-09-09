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
     * @param pid
     * @return
     */
    Product findByPId(Integer pid);

    List<Product> list();

}

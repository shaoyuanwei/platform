package com.itsyw.service.impl;

import com.itsyw.dao.ProductDao;
import com.itsyw.domain.Product;
import com.itsyw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPId(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public List<Product> list() {
        return productDao.findAll();
    }
}

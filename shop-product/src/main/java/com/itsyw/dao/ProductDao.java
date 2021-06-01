package com.itsyw.dao;

import com.itsyw.domain.Product;
import com.itsyw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
}

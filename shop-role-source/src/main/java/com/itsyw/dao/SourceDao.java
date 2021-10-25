package com.itsyw.dao;

import com.itsyw.domain.shop.ShopSource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/21 10:02
 * @Version: 1.0
 * TODO:
 */
public interface SourceDao extends JpaRepository<ShopSource, Integer> {
}

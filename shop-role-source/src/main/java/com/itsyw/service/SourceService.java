package com.itsyw.service;

import com.itsyw.domain.shop.ShopSource;

import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/21 10:04
 * @Version: 1.0
 * TODO:
 */
public interface SourceService {

    ShopSource save(ShopSource source);

    List<ShopSource> find();
}

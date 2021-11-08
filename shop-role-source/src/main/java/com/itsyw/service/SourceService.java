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

    /**
     * 保存目录资源
     *
     * @param source 资源目录对象
     * @return ShopSource
     */
    ShopSource save(ShopSource source);

    /**
     * 查找所有目录资源
     *
     * @return List<ShopSource>
     */
    List<ShopSource> find();
}

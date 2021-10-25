package com.itsyw.service.impl;

import com.itsyw.dao.SourceDao;
import com.itsyw.domain.shop.ShopSource;
import com.itsyw.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/21 10:05
 * @Version: 1.0
 * TODO:
 */
@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceDao sourceDao;

    @Override
    public ShopSource save(ShopSource source) {
        if (source.getId() == null) {
            source.setCreatedTime(new Date());
        }
        source.setUpdateTime(new Date());
        return sourceDao.save(source);
    }

    @Override
    public List<ShopSource> find() {
        return sourceDao.findAll();
    }

}

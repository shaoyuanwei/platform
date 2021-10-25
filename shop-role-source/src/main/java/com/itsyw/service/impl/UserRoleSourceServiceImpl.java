package com.itsyw.service.impl;

import com.itsyw.dao.UserRoleSourceDao;
import com.itsyw.service.UserRoleSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 16:17
 * @Version: 1.0
 * TODO:
 */
@Service
public class UserRoleSourceServiceImpl implements UserRoleSourceService {

    @Autowired
    private UserRoleSourceDao userRoleSourceDao;

    @Override
    public List<Map<String, Object>> findRoleSourceByUser(String username, Integer parentId) {
        return userRoleSourceDao.findRoleSourceByUser(username, parentId);
    }

    @Override
    public List<Map<String, Object>> findSourceByRoleId(Integer roleId) {
        return userRoleSourceDao.findSourceByRoleId(roleId);
    }

    @Override
    public List<Map<String, Object>> findRoleBySourceId(Integer sourceId) {
        return  userRoleSourceDao.findRoleBySourceId(sourceId);
    }
}

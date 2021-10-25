package com.itsyw.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 16:16
 * @Version: 1.0
 * TODO:
 */
public interface UserRoleSourceService {

    List<Map<String, Object>> findRoleSourceByUser(String username, Integer parentId);

    List<Map<String, Object>> findSourceByRoleId(Integer roleId);

    List<Map<String, Object>> findRoleBySourceId(Integer sourceId);
}

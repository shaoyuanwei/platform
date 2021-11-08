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

    /**
     * 根据用户名及目录资源父ID查找角色资源
     *
     * @param username 用户名
     * @param parentId 目录资源父ID
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> findRoleSourceByUser(String username, Integer parentId);

    /**
     * 根据角色ID找目录资源
     *
     * @param roleId 角色ID
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> findSourceByRoleId(Integer roleId);

    /**
     * 根据目录资源ID找角色
     *
     * @param sourceId 目录资源ID
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> findRoleBySourceId(Integer sourceId);
}

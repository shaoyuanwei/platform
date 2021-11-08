package com.itsyw.controller;

import com.itsyw.domain.shop.ShopRole;
import com.itsyw.domain.shop.ShopSource;
import com.itsyw.service.RoleService;
import com.itsyw.service.SourceService;
import com.itsyw.service.UserRoleSourceService;
import com.itsyw.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 16:28
 * @Version: 1.0
 * TODO:
 */
@RestController
@Slf4j
public class UserRoleSourceController {

    @Autowired
    private UserRoleSourceService userRoleSourceService;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private RoleService roleService;

    /**
     * 角色信息列表查询
     *
     * @return R
     */
    @GetMapping("/role/list")
    public R roleList() {
        List<ShopRole> shopRoles = roleService.find();
        return R.ok().put("rows", shopRoles);
    }

    /**
     * 目录资源信息列表查询
     *
     * @return R
     */
    @GetMapping("/source/list")
    public R sourceList() {
        List<ShopSource> shopSources = sourceService.find();
        return R.ok().put("rows", shopSources);
    }

    /**
     * 根据角色id查目录资源信息
     *
     * @param roleId 角色Id
     * @return R
     */
    @GetMapping("/role/source/list")
    public R roleSourceList(@RequestParam("roleId") Integer roleId) {
        List<Map<String, Object>> sources = userRoleSourceService.findSourceByRoleId(roleId);
        return R.ok().put("rows", sources);
    }

    /**
     * 根据目录资源id查有关角色信息
     *
     * @param sourceId 目录资源Id
     * @return R
     */
    @GetMapping("/source/role/list")
    public R sourceRoleList(@RequestParam("sourceId") Integer sourceId) {
        List<Map<String, Object>> sources = userRoleSourceService.findRoleBySourceId(sourceId);
        return R.ok().put("rows", sources);
    }

    /**
     * 增加新的目录资源
     *
     * @param source 目录资源
     * @return R
     */
    @PostMapping("/source/save")
    public R saveSource(ShopSource source) {
        sourceService.save(source);
        return R.ok();
    }

    /**
     * 增加新的角色信息
     *
     * @param role 角色信息
     * @return R
     */
    @PostMapping("/role/save")
    public R saveRole(ShopRole role) {
        ShopRole save = roleService.save(role);
        if (save == null) {
            return R.error("已存在该角色");
        }
        return R.ok();
    }

    /**
     * 根据用户名获取相应权限
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/getSource")
    public R getSources(@RequestParam("username") String username) {
        log.info("username:{}", username);
        List<Map<String, Object>> sources = duplexList(0, username);

        return R.ok().put("rows", sources);
    }

    /**
     * 根据用户名获取响应权限的目录信息，组成树形结构
     *
     * @param parentId 父id
     * @param username 用户名
     * @return l
     */
    private List<Map<String, Object>> duplexList(Integer parentId, String username) {

        List<Map<String, Object>> sources = userRoleSourceService.findRoleSourceByUser(username, parentId);
        List<Map<String, Object>> l = new ArrayList<>();

        if (sources.size() == 0) {
            return null;
        }
        for (Map<String, Object> source : sources) {
            Map<String, Object> m = new HashMap<>(16);
            m.put("source", source);
            List<Map<String, Object>> list = duplexList((Integer) source.get("id"), username);
            if (list != null) {
                m.put("children", list);
            }
            l.add(m);
        }

        return l;
    }

}

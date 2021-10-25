package com.itsyw.dao;

import com.itsyw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/19 15:42
 * @Version: 1.0
 * TODO:
 */
public interface UserRoleSourceDao extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true,
            value = "select DISTINCT(e.source),e.icon,e.url,e.type,e.parent_id,e.id  from shop_user a " +
                    "LEFT JOIN shop_user_role b ON a.uid = b.user_id " +
                    "LEFT JOIN shop_role c ON b.role_id = c.id " +
                    "LEFT JOIN shop_role_source d ON c.id = d.role_id " +
                    "LEFT JOIN shop_source e ON e.id = d.source_id " +
                    "where username = :username and e.parent_id = :parentId")
    List<Map<String, Object>> findRoleSourceByUser(String username, Integer parentId);

    @Query(nativeQuery = true,
    value = "" +
            "SELECT " +
            "a.id AS roleId, " +
            "c.id AS sourceId, " +
            "parent_id, " +
            "source, " +
            "type, " +
            "url " +
            "FROM " +
            "shop_role a " +
            "LEFT JOIN shop_role_source b ON a.id = b.role_id " +
            "LEFT JOIN shop_source c ON b.source_id = c.id " +
            "WHERE " +
            "a.id = :roleId")
    List<Map<String, Object>> findSourceByRoleId(Integer roleId);

    @Query(nativeQuery = true,
    value = "" +
            "SELECT " +
            "a.id AS sourceId, " +
            "c.id AS roleId, " +
            "c.role_name, " +
            "c.remark " +
            "FROM " +
            "shop_source a " +
            "LEFT JOIN shop_role_source b ON a.id = b.source_id " +
            "LEFT JOIN shop_role c ON b.role_id = c.id " +
            "WHERE " +
            "a.id = :sourceId")
    List<Map<String, Object>> findRoleBySourceId(Integer sourceId);
}

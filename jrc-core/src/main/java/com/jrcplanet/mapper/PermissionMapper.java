package com.jrcplanet.mapper;

import com.jrcplanet.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    /**
     * 根据角色id获取权限
     * @param roleId 角色id
     * @return List<Permission>
     */
    List<Permission> getPermissionByRole(String roleId);

    /**
     * 获取权限地址列表
     * @return
     */
    List<String> getPermUrls();

    List<Permission> getChildrenByParent(@Param("parentId") String parentId);

    int deleteById(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectById(String id);

    int updateByIdSelective(Permission record);

    int updateById(Permission record);
}
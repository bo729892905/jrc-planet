package com.jrcplanet.mapper;

import com.jrcplanet.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层接口
 * Created by rxb on 2016/1/28.
 */
@Repository
public interface PermissionMapper {
    /**
     * 新建权限
     * @param permission Permission
     * @return int
     */
    int insertPermission(Permission permission);

    /**
     * 根据角色id获取权限
     * @param roleId 角色id
     * @return List<Permission>
     */
    List<Permission> getPermissionByRole(String roleId);
}

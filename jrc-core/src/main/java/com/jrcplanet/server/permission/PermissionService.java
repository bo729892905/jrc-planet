package com.jrcplanet.server.permission;

import com.jrcplanet.domain.Permission;

import java.util.List;

/**
 * 权限业务层操作接口
 * Created by rxb on 2016/1/28.
 */
public interface PermissionService {
    /**
     * 新建权限
     * @param permission 权限
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

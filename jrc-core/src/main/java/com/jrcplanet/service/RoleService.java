package com.jrcplanet.service;

import com.jrcplanet.domain.Role;

import java.util.List;

/**
 * 角色业务层操作
 * Created by rxb on 2016/1/28.
 */
public interface RoleService {
    /**
     * 新增用户
     * @param role 角色
     * @return int
     */
    int insertRole(Role role);

    /**
     * 根据用户查询角色
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> getRoleByUserId(String userId);

    /**
     * 给角色设置权限
     * @param roleId 角色id
     * @param perIdList 权限id列表
     * @return int
     */
    int setPermissionsToRole(String roleId, List<String> perIdList);
}

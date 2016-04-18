package com.jrcplanet.domain;

import java.io.Serializable;

/**
 * 角色权限关联
 * Created by rxb on 2016/1/28.
 */
public class RolePermission implements Serializable {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}

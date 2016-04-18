package com.jrcplanet.domain;

import java.io.Serializable;

/**
 * 用户角色关联信息
 * Created by rxb on 2016/1/28.
 */
public class UserRole implements Serializable {
    /**
     * 用户
     */
    private String userId;
    /**
     * 角色
     */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

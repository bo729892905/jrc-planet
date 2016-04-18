package com.jrcplanet.server.role.impl;

import com.jrcplanet.domain.Role;
import com.jrcplanet.mapper.role.RoleMapper;
import com.jrcplanet.server.role.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色业务层操作实现类
 * Created by rxb on 2016/1/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    @Override
    public List<Role> getRoleByUserId(String userId) {
        return roleMapper.getRoleByUserId(userId);
    }

    @Override
    public int setPermissionsToRole(String roleId, List<String> perIdList) {
        return roleMapper.setPermissionsToRole(roleId, perIdList);
    }
}

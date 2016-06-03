package com.jrcplanet.service.impl;

import com.github.pagehelper.PageHelper;
import com.jrcplanet.domain.Role;
import com.jrcplanet.mapper.RoleMapper;
import com.jrcplanet.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色业务层操作实现类
 * Created by rxb on 2016/1/28.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

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

    @Override
    public List<Role> getRoleList(Role role) {
        PageHelper.startPage(role.getPage(), role.getRows());
        return roleMapper.getRoleList(role);
    }
}

package com.jrcplanet.service.impl;

import com.github.pagehelper.PageHelper;
import com.jrcplanet.domain.Role;
import com.jrcplanet.mapper.RoleMapper;
import com.jrcplanet.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        int result = 0;
        roleMapper.insert(role);
        String[] permIds = role.getPermIds();
        if (permIds != null && permIds.length > 0) {
            result = roleMapper.relatePermsToRole(role.getId(), permIds);
        }
        return result;
    }

    @Override
    public List<Role> getRoleByUserId(String userId) {
        return roleMapper.getRoleByUserId(userId);
    }

    @Override
    public List<Role> getRoleList(Role role) {
        PageHelper.startPage(role.getPage(), role.getRows());
        return roleMapper.getRoleList(role);
    }

    @Override
    public List<Role> getRoleCombo() {
        return roleMapper.getRoleCombo();
    }
}

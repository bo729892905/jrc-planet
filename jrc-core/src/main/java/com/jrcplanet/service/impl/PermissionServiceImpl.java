package com.jrcplanet.service.impl;

import com.jrcplanet.domain.Permission;
import com.jrcplanet.mapper.PermissionMapper;
import com.jrcplanet.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限业务层操作实现类
 * Created by rxb on 2016/1/28.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public int insertPermission(Permission permission) {
        return permissionMapper.insertPermission(permission);
    }

    @Override
    public List<Permission> getPermissionByRole(String roleId) {
        return permissionMapper.getPermissionByRole(roleId);
    }
}

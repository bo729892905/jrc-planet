package com.jrcplanet.service.impl;

import com.jrcplanet.domain.Permission;
import com.jrcplanet.mapper.PermissionMapper;
import com.jrcplanet.service.PermissionService;
import com.jrcplanet.util.ClassUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Override
    public List<String> getPermUrls() {
        return permissionMapper.getPermUrls();
    }

    @Override
    public void autoSavePerm() {
        List<String> urls = getPermUrls();

        try {
            String packageName = "com.jrcplanet.controller";
            List<String> classNames = ClassUtil.getClassName(packageName, true);
            if (classNames != null) {
                for (String className : classNames) {
                    Class clazz = Class.forName(className);
                    List<RequiresPermissions> mappings = ClassUtil.getMethodAnnotations(clazz, RequiresPermissions.class);
                    mappings.forEach(mapping -> {
                        String permVal = mapping.value()[0];
                        if (!urls.contains(permVal)) {
                            logger.info("==> 保存权限 ur : " + permVal + "...");
                            Permission permission = new Permission();
                            permission.setUrl(permVal);
                            permission.setName("");
                            permission.setVisible(true);
                            insertPermission(permission);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Permission> getPermissions(String id) {
        List<Permission> permissionList = getChildrenByParent(id);
        permissionList.forEach(permission -> permission.setChildren(getPermissions(permission.getId())));
        return permissionList;
    }

    @Override
    public List<Permission> getChildrenByParent(String parentId) {
        return permissionMapper.getChildrenByParent(parentId);
    }
}

package com.jrcplanet.mapper;

import com.jrcplanet.BaseTest;
import com.jrcplanet.domain.Permission;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxb on 2016/5/30.
 */
public class PermissionMapperTest extends BaseTest {
    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void testGetPermNames() throws Exception {
        List<String> permissions = permissionMapper.getPermUrls();
        permissions.forEach(System.out::print);
    }

    @Test
    public void testGetChildrenByParent() throws Exception {
        List<Permission> permissions = permissionMapper.getChildrenByParent(null);
        permissions.forEach(System.out::print);
    }
}
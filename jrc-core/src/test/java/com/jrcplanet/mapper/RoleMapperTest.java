package com.jrcplanet.mapper;

import com.jrcplanet.BaseTest;
import com.jrcplanet.domain.Role;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by rxb on 2016/6/3.
 */
public class RoleMapperTest extends BaseTest {
    @Resource
    private RoleMapper roleMapper;

    @Test
    public void testInsertRole() throws Exception {
        Role role = new Role();
        role.setName("管理员");
        role.setCode("admin");
        roleMapper.insertRole(role);
    }
}
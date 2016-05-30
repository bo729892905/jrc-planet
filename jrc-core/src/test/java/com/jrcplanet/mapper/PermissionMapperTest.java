package com.jrcplanet.mapper;

import com.jrcplanet.BaseTest;
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
        for (String permission : permissions) {
            System.out.println(permission);
        }
    }
}
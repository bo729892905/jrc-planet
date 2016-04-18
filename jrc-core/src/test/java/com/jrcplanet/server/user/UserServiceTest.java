package com.jrcplanet.server.user;

import com.jrcplanet.domain.User;
import com.jrcplanet.server.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户测试类
 * Created by rxb on 2016/4/18.
 */
public class UserServiceTest extends BaseTest {
    @Resource
    private UserService userService;

    @Test
    public void testInsertUser() throws Exception {

    }

    @Test
    public void testGetUserList() throws Exception {
        List<User> userList = userService.getUserList();
        List<User> userList1 = userService.getUserList();
        userList.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void testGetUserByUsername() throws Exception {

    }

    @Test
    public void testSetRolesToUser() throws Exception {

    }
}
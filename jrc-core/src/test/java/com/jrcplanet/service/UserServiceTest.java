package com.jrcplanet.service;

import com.jrcplanet.BaseTest;
import com.jrcplanet.domain.User;
import com.jrcplanet.util.EncryptUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
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
        User user = new User();
        user.setUsername("test");
        user.setPassword(EncryptUtil.encryptMD5("123456"));
        user.setRealName("测试");
        user.setGender(0);
        user.setMobilePhone("123456");
        user.setEmail("123@qq.com");
        user.setSalt("123");
        user.setRegisterDate(new Date());

        userService.insertUser(user);

    }

    @Test
    public void testGetUserList() throws Exception {
        /*List<User> userList = userService.getUserList(0, 10);
        userList.forEach(user -> System.out.println(user.toString()));*/
    }

    @Test
    public void testGetUserByUsername() throws Exception {

    }

    @Test
    public void testSetRolesToUser() throws Exception {

    }
}
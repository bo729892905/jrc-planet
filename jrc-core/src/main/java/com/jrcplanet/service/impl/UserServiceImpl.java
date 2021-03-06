package com.jrcplanet.service.impl;

import com.github.pagehelper.PageHelper;
import com.jrcplanet.domain.User;
import com.jrcplanet.mapper.UserMappser;
import com.jrcplanet.service.UserService;
import com.jrcplanet.util.EncryptUtil;
import com.jrcplanet.util.ValidateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMappser userMappser;

    @Transactional
    @Override
    public int insertUser(User user) {
        int result = 0;
        user.setPassword(EncryptUtil.encryptMD5(user.getPassword()));
        userMappser.insertUser(user);
        String roleId = user.getRoleId();
        if (!ValidateUtil.isEmpty(roleId)) {
            result = userMappser.relateRoleToUser(user.getId(), roleId);
        }
        return result;
    }

    @Override
    public List<User> getUserList(User user) {
        PageHelper.startPage(user.getPage(), user.getRows());
        return userMappser.getUserList(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMappser.getUserByUsername(username);
    }

    @Override
    public int setRolesToUser(String userId, List<String> roleIdList) {
        return userMappser.setRolesToUser(userId, roleIdList);
    }

    @Override
    public int deleteUser(String[] ids) {
        return userMappser.deleteUser(ids);
    }
}

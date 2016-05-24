package com.jrcplanet.service.impl;

import com.github.pagehelper.PageHelper;
import com.jrcplanet.domain.User;
import com.jrcplanet.mapper.UserMappser;
import com.jrcplanet.service.UserService;
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
        return userMappser.insertUser(user);
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
}

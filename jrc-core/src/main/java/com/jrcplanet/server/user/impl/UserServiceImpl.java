package com.jrcplanet.server.user.impl;

import com.jrcplanet.domain.User;
import com.jrcplanet.mapper.user.UserMappser;
import com.jrcplanet.server.user.UserService;
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
	public List<User> getUserList() {
		return userMappser.getUserList();
	}

	@Override
	public User getUserByUsername(String username) {
		return userMappser.getUserByUsername(username);
	}

	@Override
	public int setRolesToUser(String userId, List<String> roleIdList) {
		return userMappser.setRolesToUser(userId,roleIdList);
	}
}

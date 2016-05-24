package com.jrcplanet.service;

import com.jrcplanet.domain.User;

import java.util.List;

public interface UserService {
	/**
	 * 新建用户
	 * @param user 用户
	 * @return int
     */
	int insertUser(User user);

	/**
	 * 获取用户列表
	 * @return List<User>
	 */
	List<User> getUserList(User user);

	/**
	 * 根据用户名获取用户
	 * @param username 用户名
	 * @return User
	 */
	User getUserByUsername(String username);

	/**
	 * 给用户设置角色
	 * @param userId 用户id
	 * @param roleIdList 角色id列表
     * @return int
     */
	int setRolesToUser(String userId, List<String> roleIdList);
}

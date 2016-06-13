package com.jrcplanet.mapper;

import com.jrcplanet.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMappser {

    /**
     * 新建用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<User> getUserList(User user);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIdList
     * @return
     */
    int setRolesToUser(@Param("userId") String userId, @Param("roleIdList") List<String> roleIdList);

    int deleteUser(@Param("ids") String[] ids);

    /**
     * 将角色关联到用户
     * @param userId
     * @param roleId
     * @return
     */
    int relateRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}

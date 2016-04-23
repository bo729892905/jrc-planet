package com.jrcplanet.mapper;

import com.jrcplanet.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层接口
 * Created by rxb on 2016/1/28.
 */
@Repository
public interface RoleMapper {
    /**
     * 新增用户
     * @param role 角色
     * @return int
     */
    int insertRole(Role role);

    /**
     * 根据用户查询角色
     * @param userId 角色id
     * @return List<Role>
     */
    List<Role> getRoleByUserId(String userId);

    /**
     * 为角色设置权限
     *
     * @param roleId 角色id
     * @param perIdList 权限id列表
     * @return int
     */
    int setPermissionsToRole(@Param("roleId") String roleId, @Param("perIdList") List<String> perIdList);
}

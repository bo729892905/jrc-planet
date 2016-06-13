package com.jrcplanet.mapper;

import com.jrcplanet.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色持久层接口
 * Created by rxb on 2016/1/28.
 */
@Repository
public interface RoleMapper {
    int deleteById(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectById(String id);

    int updateByIdSelective(Role record);

    int updateById(Role record);

    /**
     * 根据用户查询角色
     *
     * @param userId 角色id
     * @return List<Role>
     */
    List<Role> getRoleByUserId(String userId);

    List<Role> getRoleList(Role role);

    /**
     * 将权限关联到角色
     * @param roleId
     * @param permIds
     * @return
     */
    int relatePermsToRole(@Param("roleId") String roleId, @Param("permIds") String[] permIds);

    /**
     * 获取角色combo
     * @return
     */
    List<Role> getRoleCombo();
}
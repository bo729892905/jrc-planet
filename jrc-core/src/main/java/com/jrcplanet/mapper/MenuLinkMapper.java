package com.jrcplanet.mapper;

import com.jrcplanet.domain.MenuLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuLinkMapper {
    int deleteById(String id);

    int insert(MenuLink record);

    int insertSelective(MenuLink record);

    MenuLink selectById(String id);

    int updateByIdSelective(MenuLink record);

    int updateById(MenuLink record);

    List<MenuLink> getRoots();

    List<MenuLink> getChildrenByParent(@Param("parentId") String parentId);
}
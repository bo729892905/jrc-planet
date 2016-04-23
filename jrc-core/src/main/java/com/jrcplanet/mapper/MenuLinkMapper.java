package com.jrcplanet.mapper;

import com.jrcplanet.domain.MenuLink;
import com.jrcplanet.model.easyui.Tree;

import java.util.List;

/**
 * 菜单持久层接口
 * Created by rxb on 2016/4/22.
 */
public interface MenuLinkMapper {
    int insertMenu(MenuLink menuLink);

    int updateMenu(MenuLink menuLink);

    int deleteMenu(String id);

    MenuLink getMenuById(String id);

    List<MenuLink> getMenuByParent(String parentId);

    Tree getMenuTree();
}

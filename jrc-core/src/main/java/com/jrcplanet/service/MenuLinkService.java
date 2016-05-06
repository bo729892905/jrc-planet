package com.jrcplanet.service;

import com.jrcplanet.domain.MenuLink;

import java.util.List;

/**
 * 菜单业务接口类
 * Created by rxb on 2016/5/5.
 */
public interface MenuLinkService {
    /**
     * 保存菜单
     * @param menuLink
     * @return
     */
    int insert(MenuLink menuLink);

    /**
     * 获取根节点
     * @return
     */
    List<MenuLink> getRoots();

    /**
     * 获取子节点
     * @param parentId
     * @return
     */
    List<MenuLink> getChildrenByParent(String parentId);
}

package com.jrcplanet.service.impl;

import com.jrcplanet.domain.MenuLink;
import com.jrcplanet.mapper.MenuLinkMapper;
import com.jrcplanet.service.MenuLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxb on 2016/5/5.
 */
@Service
public class MenuLinkServiceImpl implements MenuLinkService {
    @Resource
    private MenuLinkMapper menuLinkMapper;

    @Override
    @Transactional
    public int insert(MenuLink menuLink) {
        return menuLinkMapper.insert(menuLink);
    }

    @Override
    public List<MenuLink> getRoots() {
        return menuLinkMapper.getRoots();
    }

    @Override
    public List<MenuLink> getChildrenByParent(String parentId) {
        return menuLinkMapper.getChildrenByParent(parentId);
    }

    @Override
    public List<MenuLink> getAllMenuLink(String parentId) {
        List<MenuLink> menuLinkList = getChildrenByParent(parentId);
        menuLinkList.forEach(menuLink -> {
            menuLink.setChildren(getAllMenuLink(menuLink.getId()));
        });
        return menuLinkList;
    }

    @Override
    public int deleteById(String id) {
        return menuLinkMapper.deleteById(id);
    }
}

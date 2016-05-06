package com.jrcplanet.service;

import com.jrcplanet.BaseTest;
import com.jrcplanet.domain.MenuLink;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rxb on 2016/5/5.
 */
public class MenuLinkServiceTest extends BaseTest {
    @Resource
    private MenuLinkService menuLinkService;

    @Test
    public void testInsert() throws Exception {
        MenuLink menuLink1 = new MenuLink();
        menuLink1.setName("用户管理");
        menuLink1.setSeqNo(1);
        menuLinkService.insert(menuLink1);

        MenuLink menuLink2 = new MenuLink();
        menuLink2.setName("系统管理");
        menuLink2.setSeqNo(2);
        menuLinkService.insert(menuLink2);

        MenuLink menuLink11 = new MenuLink();
        menuLink11.setName("用户管理");
        menuLink11.setIconCls("icon-user-white");
        menuLink11.setParentId(menuLink1.getId());
        menuLink11.setSeqNo(1);
        menuLinkService.insert(menuLink11);

        MenuLink menuLink12 = new MenuLink();
        menuLink12.setName("角色管理");
        menuLink12.setIconCls("icon-hand-left-white");
        menuLink12.setParentId(menuLink1.getId());
        menuLink12.setSeqNo(2);
        menuLinkService.insert(menuLink12);

        MenuLink menuLink13 = new MenuLink();
        menuLink13.setName("权限管理");
        menuLink13.setIconCls("icon-lock-white");
        menuLink13.setParentId(menuLink1.getId());
        menuLink13.setSeqNo(3);
        menuLinkService.insert(menuLink13);

        MenuLink menuLink21 = new MenuLink();
        menuLink21.setName("用户管理");
        menuLink21.setIconCls("icon-user-white");
        menuLink21.setParentId(menuLink2.getId());
        menuLink21.setSeqNo(1);
        menuLinkService.insert(menuLink21);

        MenuLink menuLink22 = new MenuLink();
        menuLink22.setName("角色管理");
        menuLink22.setIconCls("icon-hand-left-white");
        menuLink22.setParentId(menuLink2.getId());
        menuLink22.setSeqNo(2);
        menuLinkService.insert(menuLink22);
    }

    @Test
    public void testGetRoots() throws Exception {
        List<MenuLink> menuLinks = menuLinkService.getRoots();
        menuLinks.forEach(menuLink -> {
            System.out.println(menuLink.toString());
        });
    }
}
package com.jrcplanet.controller.menulink;

import com.jrcplanet.domain.MenuLink;
import com.jrcplanet.model.easyui.Tree;
import com.jrcplanet.model.easyui.TreeNode;
import com.jrcplanet.util.TreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxb on 2016/4/23.
 */
@Controller
@RequestMapping(value = "menu")
public class MenuLinkController {
    @RequestMapping(value = "tree",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TreeNode> getMenuTree() {
        MenuLink menuLink1 = new MenuLink("1","用户管理");
        MenuLink menuLink2 = new MenuLink("2","系统管理");
        List<MenuLink> root = new ArrayList<>();
        root.add(menuLink1);
        root.add(menuLink2);

        Tree tree = TreeUtil.getInstance().formatTree(root, treeNode -> {
            List<MenuLink> children = new ArrayList<>();
            if (treeNode.getId().equals("1")) {
                MenuLink menuLink11 = new MenuLink("11", "用户管理", "icon-user-white");
                MenuLink menuLink12 = new MenuLink("12", "角色管理", "icon-hand-left-white");
                MenuLink menuLink13 = new MenuLink("13", "权限管理", "icon-lock-white");
                children.add(menuLink11);
                children.add(menuLink12);
                children.add(menuLink13);
            } else if (treeNode.getId().equals("2")) {
                MenuLink menuLink21 = new MenuLink("21", "字典管理", "icon-user-white");
                MenuLink menuLink22 = new MenuLink("22", "菜单管理", "icon-user-white");
                children.add(menuLink21);
                children.add(menuLink22);
            }
            return children;
        }, null);
        return tree.getTreeNodeList();
    }

}

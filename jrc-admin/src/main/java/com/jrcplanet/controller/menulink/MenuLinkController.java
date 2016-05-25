package com.jrcplanet.controller.menulink;

import com.jrcplanet.domain.MenuLink;
import com.jrcplanet.model.easyui.Tree;
import com.jrcplanet.model.easyui.TreeNode;
import com.jrcplanet.service.MenuLinkService;
import com.jrcplanet.util.TreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxb on 2016/4/23.
 */
@Controller
@RequestMapping(value = "menu")
public class MenuLinkController {
    @Resource
    private MenuLinkService menuLinkService;

    @RequestMapping(value = "tree", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TreeNode> getMenuTree() {
        List<MenuLink> root = menuLinkService.getRoots();

        Tree tree = TreeUtil.getInstance().formatTree(root, treeNode -> menuLinkService.getChildrenByParent(treeNode.getId()), null, true, 1);
        return tree.getTreeNodeList();
    }

    @RequestMapping(value = "treegrid", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<MenuLink> getMenuTreeGrid() {
        return menuLinkService.getAllMenuLink();
    }

}

package com.jrcplanet.controller.menulink;

import com.jrcplanet.domain.MenuLink;
import com.jrcplanet.model.easyui.JsonData;
import com.jrcplanet.model.easyui.Tree;
import com.jrcplanet.model.easyui.TreeNode;
import com.jrcplanet.service.MenuLinkService;
import com.jrcplanet.util.TreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 菜单Controller
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
    public List<MenuLink> getMenuTreeGrid(@RequestParam(value = "id", required = false) String id) {
        return menuLinkService.getAllMenuLink(id);
    }

    @RequestMapping(value = "urllist", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String[] getUrlList() {
        String basePath = getClass().getResource("/").getPath();
        File file = new File(basePath);
        File parentFile = file.getParentFile().getParentFile();
        String webappPath = parentFile.getAbsolutePath();
        String partialsFilePath = webappPath + File.separator + "partials";
        File partialsFile = new File(partialsFilePath);
        return partialsFile.list();
    }

    @RequestMapping(value = "addMenu", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonData addMenu(MenuLink menu) {
        menuLinkService.insert(menu);
        return JsonData.createSuccessData(menu);
    }
}

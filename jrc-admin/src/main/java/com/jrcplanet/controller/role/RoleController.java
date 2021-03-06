package com.jrcplanet.controller.role;

import com.github.pagehelper.Page;
import com.jrcplanet.domain.Role;
import com.jrcplanet.model.easyui.DataGrid;
import com.jrcplanet.model.easyui.JsonData;
import com.jrcplanet.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by rxb on 2016/6/3.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DataGrid getPermTreeGrid(Role role) {
        List<Role> roleList = roleService.getRoleList(role);
        @SuppressWarnings("unchecked")
        Page<Role> list = (Page) roleList;

        return new DataGrid(list.getTotal(), roleList);
    }

    @RequiresPermissions(value="role:add")
    @RequestMapping(value = "addRole", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonData insert(Role role) {
        roleService.insertRole(role);
        return JsonData.createSuccessData(role);
    }

    @RequestMapping(value = "roleCombo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Role> getRoleCombo() {
        return roleService.getRoleCombo();
    }
}

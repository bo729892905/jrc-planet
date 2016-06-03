package com.jrcplanet.controller.permission;

import com.jrcplanet.domain.MenuLink;
import com.jrcplanet.domain.Permission;
import com.jrcplanet.model.easyui.JsonData;
import com.jrcplanet.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxb on 2016/5/30.
 */
@Controller
@RequestMapping("perm")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @RequestMapping(value = "treegrid", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Permission> getPermTreeGrid(@RequestParam(value = "id", required = false) String id) {
        return permissionService.getPermissions(id);
    }

    @RequestMapping(value = "addPerm", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonData addPerm(Permission perm) {
        permissionService.insertPermission(perm);
        return JsonData.createSuccessData(perm);
    }

    @RequestMapping(value = "updatePerm", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonData updatePerm(Permission perm) {
        permissionService.updateByIdSelective(perm);
        return JsonData.createSuccessData(perm);
    }

    @RequestMapping(value = "movePerm", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonData movePerm(@RequestParam("targetId") String targetId,@RequestParam("sourceId")String sourceId) {
        permissionService.movePerm(targetId,sourceId);
        return JsonData.createSuccessData();
    }
}

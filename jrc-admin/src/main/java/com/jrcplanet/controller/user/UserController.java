package com.jrcplanet.controller.user;

import com.github.pagehelper.Page;
import com.jrcplanet.domain.User;
import com.jrcplanet.model.MonitorInfo;
import com.jrcplanet.model.SystemProperty;
import com.jrcplanet.model.easyui.DataGrid;
import com.jrcplanet.model.easyui.JsonData;
import com.jrcplanet.service.UserService;
import com.jrcplanet.util.EncryptUtil;
import com.jrcplanet.util.MathUtil;
import com.jrcplanet.util.ServerInfoUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Controller
 * Created by rxb on 2016/4/18.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SESSION_USER = "loginUser";

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return ModelAndView
     */
    @RequestMapping(value = "login")
    public ModelAndView login(String username, String password) {
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());

        UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptUtil.encryptMD5(password));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.debug("用户名或密码错误！");
            return new ModelAndView("login");
        }
        User user = userService.getUserByUsername(username);
        subject.getSession().setAttribute(SESSION_USER, user);

        logger.debug("登录成功！");
        return new ModelAndView("redirect:test.do");
    }

    @RequestMapping(value = "addUser")
    @ResponseBody
    public JsonData<User> createUser(User user) {
        userService.insertUser(user);
        return JsonData.createSuccessData(user);
    }

    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public JsonData deleteUser(@RequestParam("id") String[] id) {
        userService.deleteUser(id);
        return JsonData.createSuccessData();
    }

    @RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DataGrid getUserList(User user) {
        List<User> userList = userService.getUserList(user);
        @SuppressWarnings("unchecked")
        Page<User> list = (Page) userList;

        return new DataGrid(list.getTotal(), userList);
    }

    @RequestMapping(value = "system", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SystemProperty getSystemProperty() {
        return ServerInfoUtil.getSystemProperty();
    }

    @RequestMapping(value = "memory", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MonitorInfo getMemory() {
        return ServerInfoUtil.memory();
    }

    @RequestMapping(value = "usage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MonitorInfo getUsage() {
        return ServerInfoUtil.usage();
    }
}

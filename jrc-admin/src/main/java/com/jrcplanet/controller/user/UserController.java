package com.jrcplanet.controller.user;

import com.jrcplanet.domain.User;
import com.jrcplanet.model.easyui.DataGrid;
import com.jrcplanet.service.UserService;
import com.jrcplanet.util.EncryptUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxb on 2016/4/18.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SESSION_USER = "loginUser";

    @Resource
    private UserService userService;

    @RequiresPermissions("test1")
    @RequestMapping(value = "test", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List test() {
        return userService.getUserList();
    }

    @RequestMapping(value = "beetl.html")
    public ModelAndView testBeetl() {
        ModelAndView view = new ModelAndView();
        view.setViewName("hellobee");
        view.addObject("name","张三");
        return view;
    }

    /**
     * 登录
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

    @RequestMapping(value = "list",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DataGrid getUserList() {
        List<User> userList = userService.getUserList();
        return new DataGrid(userList.size(), userList);
    }
}

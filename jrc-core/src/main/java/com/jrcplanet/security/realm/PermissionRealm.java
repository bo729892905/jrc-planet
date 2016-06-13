package com.jrcplanet.security.realm;

import com.jrcplanet.domain.Permission;
import com.jrcplanet.domain.Role;
import com.jrcplanet.domain.User;
import com.jrcplanet.service.PermissionService;
import com.jrcplanet.service.RoleService;
import com.jrcplanet.service.UserService;
import com.jrcplanet.util.ValidateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限Realm
 * Created by rxb on 2016/4/8.
 */
public class PermissionRealm extends AuthorizingRealm {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    /**
     * 重写授权方法
     *
     * @param p PrincipalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection p) {
        logger.info("doGetAuthorizationInfo....principals: " + p.toString());
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");

        Set<String> roleCodeSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();

        //获取用户权限
        List<Role> roleList = roleService.getRoleByUserId(user.getId());

        roleList.forEach(role -> {
            roleCodeSet.add(role.getCode());
            List<Permission> permissionList = permissionService.getPermissionByRole(role.getId());
            permissionList.forEach(e -> {
                String url = e.getUrl();
                if (!ValidateUtil.isEmpty(url)) {
                    permissionSet.add(url);
                }
            });
        });

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleCodeSet);
        info.setStringPermissions(permissionSet);

        return info;
    }

    /**
     * 重写认证方法
     *
     * @param token AuthenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo...token: " + token.toString());

        String username = (String) token.getPrincipal();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new AuthenticationException();
        }

        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }
}

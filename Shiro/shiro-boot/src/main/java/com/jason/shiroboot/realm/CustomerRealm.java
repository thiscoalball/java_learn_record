package com.jason.shiroboot.realm;

import com.jason.shiroboot.pojo.Permission;
import com.jason.shiroboot.pojo.Role;
import com.jason.shiroboot.pojo.User;
import com.jason.shiroboot.service.PermissionService;
import com.jason.shiroboot.service.RoleService;
import com.jason.shiroboot.service.UserService;
import com.jason.shiroboot.util.ApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取主身份信息
        String principal = (String) principalCollection.getPrimaryPrincipal();
        // 根据主身份信息获取用户
        User user = userService.findUserByUserName(principal);
        //根据用户id获取角色
        List<Role> roles = roleService.getRolesByUserId(user.getId());

        //添加角色
        if(!CollectionUtils.isEmpty(roles)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                //获取该角色的权限列表
                List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
                //添加为角色权限
                if(!CollectionUtils.isEmpty(permissions)) {
                    permissions.forEach(permission -> {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取当前登录的主题
        String principal = (String) token.getPrincipal();
        User user = userService.findUserByUserName(principal);
        // 模拟数据库返回的数据
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt())
                    ,this.getName());
        }
        return null;
    }
}

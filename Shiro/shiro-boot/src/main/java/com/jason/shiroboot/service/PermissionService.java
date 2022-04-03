package com.jason.shiroboot.service;

import com.jason.shiroboot.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissionsByRoleId(Integer roleId);
}

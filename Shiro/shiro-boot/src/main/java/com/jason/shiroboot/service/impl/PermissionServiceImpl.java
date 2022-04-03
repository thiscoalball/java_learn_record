package com.jason.shiroboot.service.impl;

import com.jason.shiroboot.mapper.PermissionMapper;
import com.jason.shiroboot.pojo.Permission;
import com.jason.shiroboot.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return permissionMapper.getPermissionsByRoleId(roleId);
    }
}

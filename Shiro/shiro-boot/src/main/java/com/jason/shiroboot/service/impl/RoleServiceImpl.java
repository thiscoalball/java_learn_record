package com.jason.shiroboot.service.impl;

import com.jason.shiroboot.mapper.RoleMapper;
import com.jason.shiroboot.pojo.Role;
import com.jason.shiroboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return roleMapper.getRolesByUserId(userId);
    }
}

package com.jason.shiroboot.service;

import com.jason.shiroboot.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByUserId(Integer userId);
}

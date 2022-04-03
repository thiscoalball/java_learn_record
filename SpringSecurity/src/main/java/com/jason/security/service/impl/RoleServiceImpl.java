package com.jason.security.service.impl;

import com.jason.security.mapper.UserMapper;
import com.jason.security.pojo.Role;
import com.jason.security.mapper.RoleMapper;
import com.jason.security.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jason
 * @since 2021-11-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectById(Integer roleId) {
        return roleMapper.selectById(roleId);
    }
}

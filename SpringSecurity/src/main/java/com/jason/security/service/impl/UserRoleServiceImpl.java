package com.jason.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.security.pojo.UserRole;
import com.jason.security.mapper.UserRoleMapper;
import com.jason.security.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jason
 * @since 2021-11-09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    private List<UserRole> listByUserId(int userId){
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        return userRoleMapper.selectList(new QueryWrapper<>(userRole));
    }
}

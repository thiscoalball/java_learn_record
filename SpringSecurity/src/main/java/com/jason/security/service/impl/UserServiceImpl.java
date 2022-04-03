package com.jason.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.security.pojo.User;
import com.jason.security.mapper.UserMapper;
import com.jason.security.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private UserMapper userMapper;


    public User selectById(int id){
        return userMapper.selectById(id);
    }

    public User selectByName(String name){
        User user = new User();
        user.setName(name);
        return userMapper.selectOne(new QueryWrapper<>(user));
    }
}

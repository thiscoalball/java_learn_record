package com.jason.shiroboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.shiroboot.mapper.UserMapper;
import com.jason.shiroboot.pojo.User;
import com.jason.shiroboot.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void register(User user) {
        // 生成盐
        String salt = "1a2b3c4d";
        // 保存随机盐
        user.setSalt(salt);
        // 生成密码
        Md5Hash password = new Md5Hash(user.getPassword(), salt, 1024);
        // 保存密码
        user.setPassword(password.toHex());
        userMapper.insert(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        return userMapper.selectOne(new QueryWrapper<>(user));
    }
}

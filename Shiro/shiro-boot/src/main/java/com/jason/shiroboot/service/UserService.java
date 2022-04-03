package com.jason.shiroboot.service;

import com.jason.shiroboot.pojo.User;
import org.springframework.context.annotation.Bean;


public interface UserService {
    void register(User user);
    User findUserByUserName(String userName);
}

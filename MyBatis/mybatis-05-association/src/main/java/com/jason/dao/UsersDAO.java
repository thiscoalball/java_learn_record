package com.jason.dao;

import com.jason.pojo.Users;


public interface UsersDAO {
    public int insertUser(Users users);
    //根据用户名查询用户消息
    public Users queryUser(String name);
}

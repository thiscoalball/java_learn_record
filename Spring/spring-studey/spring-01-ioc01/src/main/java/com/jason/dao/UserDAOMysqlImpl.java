package com.jason.dao;

public class UserDAOMysqlImpl implements UserDAO{
    @Override
    public void getUser() {
        System.out.println("mysql获取用户数据");
    }
}

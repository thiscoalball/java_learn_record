package com.jason.dao;

import com.jason.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //模糊查询
    List<User> getUserLik(String name);
    //查询全部用户
    List<User> getUserList();
    //根据id查询用户
    User getUserById(int id);
    //插入一个用户
    int insertUser(User user);
    //修改用户
    int updateUser(User user);
    //删除一个用户
    int deleteUser(int id);
    //万能的map
    int addUser2(Map<String, Object> map);
}

package com.jason.dao;

import com.jason.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    List<User> findAll();
    int deleteById(int id);
}

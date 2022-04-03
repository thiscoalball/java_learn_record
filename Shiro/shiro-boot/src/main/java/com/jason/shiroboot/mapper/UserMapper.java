package com.jason.shiroboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.shiroboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

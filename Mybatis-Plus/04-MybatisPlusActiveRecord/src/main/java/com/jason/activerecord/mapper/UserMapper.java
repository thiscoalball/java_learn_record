package com.jason.activerecord.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.activerecord.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

}

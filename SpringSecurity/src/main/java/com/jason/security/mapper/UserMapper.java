package com.jason.security.mapper;

import com.jason.security.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jason
 * @since 2021-11-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

package com.jason.shiroboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.shiroboot.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role>{
    @Select("select r.id,r.name from t_role r " +
            "left join t_user_role ur " +
            "on ur.role_id = r.id" +
            " where ur.user_id = #{userId}")
    List<Role> getRolesByUserId(Integer userId);

}

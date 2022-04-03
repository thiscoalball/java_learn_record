package com.jason.shiroboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.shiroboot.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select p.id,p.name,p.url from t_permission p " +
            "left join t_role_permission rp " +
            "on rp.permission_id = p.id " +
            "where rp.role_id = #{roleId}")
    List<Permission> getPermissionsByRoleId(Integer roleId);
}

package com.jason.security.service;

import com.jason.security.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jason
 * @since 2021-11-09
 */
public interface IRoleService extends IService<Role> {

    Role selectById(Integer roleId);
}

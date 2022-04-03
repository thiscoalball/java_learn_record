package com.jason.security.service;

import com.jason.security.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jason
 * @since 2021-11-09
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> listByUserId(Integer id);
}

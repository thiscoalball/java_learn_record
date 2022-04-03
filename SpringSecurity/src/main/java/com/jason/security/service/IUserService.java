package com.jason.security.service;

import com.jason.security.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jason
 * @since 2021-11-09
 */
public interface IUserService extends IService<User> {

    User selectByName(String username);
}

package com.jason.dao;

import com.jason.pojo.UsersDetails;

public interface UsersDetailsDAO {
    public int insertDetail(UsersDetails usersDetails);

    public UsersDetails queryDetailByUid(int uid);
}

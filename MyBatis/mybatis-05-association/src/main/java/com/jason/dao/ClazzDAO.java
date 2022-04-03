package com.jason.dao;

import com.jason.pojo.Clazz;

public interface ClazzDAO {
    //根据班级编号查询班级消息
    public Clazz queryClassStudent(int classId);
    //根据班级查询班级信息
    public Clazz queryClazz(int classId);
}

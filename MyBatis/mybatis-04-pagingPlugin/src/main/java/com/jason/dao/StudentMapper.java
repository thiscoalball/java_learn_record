package com.jason.dao;

import com.jason.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    //查询全部用户
    List<Student> getStudentList();
    //根据id查询用户
    Student getStudentById(@Param("stu_id")int id);
    //插入一个用户
    int insertStudent(Student student);
    //修改用户
    int updateStudent(Student student);
    //删除一个用户
    int deleteStudent(@Param("stu_id")int id);
    //带条件分页查询 根据性别
    List<Student> getStudentListByGender(@Param("stu_gender") String gender);
}

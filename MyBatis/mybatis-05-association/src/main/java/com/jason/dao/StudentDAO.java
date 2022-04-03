package com.jason.dao;

import com.jason.pojo.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> queryStudentByClassId(int classID);
    public Student queryStudentByStudentId(String studentId);
    public List<Student> queryStudentsByCourseId(String courseId);
}

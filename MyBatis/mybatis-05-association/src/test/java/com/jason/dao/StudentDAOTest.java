package com.jason.dao;

import com.jason.pojo.Clazz;
import com.jason.pojo.Student;
import com.jason.utils.MybatisUtils;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentDAOTest extends TestCase {

    @Test
    public void testQueryStudentByStudentId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDAO mapper = sqlSession.getMapper(StudentDAO.class);
        Student student = mapper.queryStudentByStudentId("10001");
        System.out.println(student);
        sqlSession.close();
    }
}
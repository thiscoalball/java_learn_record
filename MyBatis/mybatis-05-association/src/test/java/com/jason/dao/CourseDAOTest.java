package com.jason.dao;

import com.jason.pojo.Clazz;
import com.jason.pojo.Course;
import com.jason.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CourseDAOTest {
    @Test
    public void queryCourseById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        CourseDAO mapper = sqlSession.getMapper(CourseDAO.class);
        Course course = mapper.queryCourseById(1);
        System.out.println(course);
        sqlSession.close();
    }
}

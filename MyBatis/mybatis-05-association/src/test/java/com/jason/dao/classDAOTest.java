package com.jason.dao;

import com.jason.pojo.Clazz;
import com.jason.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class classDAOTest {
    @Test
    public void testQueryClass(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ClazzDAO mapper = sqlSession.getMapper(ClazzDAO.class);
        Clazz clazz = mapper.queryClassStudent(2);
        System.out.println(clazz);
        sqlSession.close();
    }

}

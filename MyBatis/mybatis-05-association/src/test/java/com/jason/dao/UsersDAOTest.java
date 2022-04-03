package com.jason.dao;

import com.alibaba.fastjson.JSON;
import com.jason.pojo.Users;
import com.jason.pojo.UsersDetails;
import com.jason.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UsersDAOTest {
    @Test
    public void testInsertUser(){
        Users users = new Users(0, "zhaoliu", "123123", "赵六", "03.jpg",null);
        UsersDetails usersDetails = new UsersDetails(0, "福建省福州市", "1313131331", "好个棒棒糖", 0);

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            UsersDAO usersDAO = sqlSession.getMapper(UsersDAO.class);
            int i = usersDAO.insertUser(users);
            usersDetails.setUid(users.getUserId());
            UsersDetailsDAO detailsDAO = sqlSession.getMapper(UsersDetailsDAO.class);
            int i1 = detailsDAO.insertDetail(usersDetails);
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testQueryUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UsersDAO usersDAO = sqlSession.getMapper(UsersDAO.class);
        Users users = usersDAO.queryUser("wangwu");
        System.out.println(users);
        System.out.println(JSON.toJSONString(users));
        sqlSession.close();
    }

}

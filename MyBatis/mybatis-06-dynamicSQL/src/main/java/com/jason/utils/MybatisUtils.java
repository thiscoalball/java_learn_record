package com.jason.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//工具类
public class MybatisUtils {
//  静态代码块
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    有了静态代码块生成的工厂后 我们就可以从中获取sqlSession的实例了 SqlSession包含了面向数据库执行SQL命令的所有方法
    public static SqlSession getSqlSession(){
        return  sqlSessionFactory.openSession();
    }

    public static <T extends Object>T getMapper(Class<T> c){
        SqlSession sqlSession = getSqlSession();
        return sqlSession.getMapper(c);
    }
}

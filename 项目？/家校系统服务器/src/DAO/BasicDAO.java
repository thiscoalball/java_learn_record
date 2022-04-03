package DAO;

import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 是其他DAO的父类
 * */
@SuppressWarnings({"all"})
public class BasicDAO<T> {//泛型指定具体类型

    //创建
    private QueryRunner qr =  new QueryRunner();

    //开发通用的DML针对任意的表          可变参数
    public int update(String sql,Object... param) throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();

        int update = qr.update(connection, sql, param);
        JDBCUtilsByDruid.close(null,null,connection);
        return update;
    }

    //返回结果集 (参数：sql语句     类的类型    可变参数[？替代查询的地方] )
    public List<T> queryMulti(String sql, Class<T> clazz, Object...param) throws SQLException {
        //创建链接
        Connection connection = JDBCUtilsByDruid.getConnection();
        List<T> query = qr.query(connection, sql, new BeanListHandler<T>(clazz), param);
        //关闭链接
        JDBCUtilsByDruid.close(null,null,connection);
        return query;
    }

    //查询单行结果
    public T querySingle(String sql, Class<T> clazz, Object...param) throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        T query = qr.query(connection, sql, new BeanHandler<T>(clazz), param);

        JDBCUtilsByDruid.close(null,null,connection);
        return query;
    }

    //查询单行单列的结构
    public Object queryScalar(String sql,  Object...param) throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        Object query = qr.query(connection, sql, new ScalarHandler<>(), param);
        JDBCUtilsByDruid.close(null,null,connection);
        return query;
    }
}

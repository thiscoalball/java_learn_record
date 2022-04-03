package Tracsaction;

import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction_ {
    public static void main(String[] args) throws Exception {

        Connection connection = null;
        connection = JDBCUtils.getConnection();
        try {
            //为了解决后面说的抛出异常时的问题 这里进行这个设置 这样后面的异常就不会让数据库进行处理了
            //一旦发生异常 就直接回滚
            connection.setAutoCommit(false);
            String sql = "update account set balance = balance - 100 where id =1";
            String sql2 = "update account set balance = balance + 100 where id = 2";

            connection.prepareStatement(sql).executeUpdate();
            int i = 1/0;//故意写一个代码抛出异常 这时候第一条sql语句执行了 但是第二条没执行 所以我们需要事务处理
            connection.prepareStatement(sql2).executeUpdate();

            //这里提交事务
            connection.commit();
        } catch (SQLException e) {
            //这里可以回滚
            System.out.println("执行发生异常撤销代码里的sql语句");
            connection.rollback();
            System.out.println(e);
        } finally {
            JDBCUtils.close(null,null,connection);

        }
    }
}



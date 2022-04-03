package Batch;

import org.junit.jupiter.api.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch_ {


    @Test
    public void noBatch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("开始执行");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString (1,"jack"+i);
            preparedStatement.setString(2,"666");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统的方法耗时:"+(end-start));//耗时3632
        JDBCUtils.close(null,preparedStatement,connection);
    }

    @Test
    public void batch() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("开始执行");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString (1,"jack"+i);
            preparedStatement.setString(2,"666");
            //将sql语句加入到批处理包中 ----->这里需要先去改变url不然用不了
            preparedStatement.addBatch();
            //当有1000条记录时再批量执行
            if((i+1) % 1000 == 0){
                preparedStatement.executeBatch();
                //清空一把
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批量的方法耗时:"+(end-start));//耗时112
        JDBCUtils.close(null,preparedStatement,connection);
    }
}

package dateSourse;

import java.sql.*;
import java.util.Date;

public class JDBCUTilsByDruid_Use {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();

        Statement statement = connection.createStatement();
        String sql = "select id,name,sex,borndate from actor";
        //获取结果集
        ResultSet resultSet = statement.executeQuery(sql);
        //使用while取出数据
        while(resultSet.next()){
            int id = resultSet.getInt(1); //该行第一列
            String name = resultSet.getString(2);//该行第二列
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }
        JDBCUtilsByDruid.close(resultSet,statement,connection);
    }
}

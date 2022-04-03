package utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils_User {
    public static void main(String[] args) {

    }
    @Test
    public void testDML() throws Exception {
        Connection connection = JDBCUtils.getConnection();

        String sql = "update actor set name = ? where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "周星驰");
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }
    }
    @Test
    public void testSelect() throws Exception {
        Connection connection = JDBCUtils.getConnection();

        String sql = "select id,name,sex from actor";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            System.out.println(id + "\t" + name + "\t" + sex );
        }
        JDBCUtils.close(resultSet,null,connection);
    }
}

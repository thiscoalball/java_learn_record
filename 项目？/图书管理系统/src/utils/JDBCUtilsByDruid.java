package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 基于德鲁伊数据库连接池的工具类
 */
public class JDBCUtilsByDruid {
    private static DataSource ds;

    static {
        //创建一个配置文件类
        Properties properties = new Properties();
        try {
            //加载配置文件
            properties.load(new FileInputStream("src\\druid.properties"));

            //将配置文件传入德鲁伊连接池工厂类 ---- 创建数据库连接池
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接数据库
    public static Connection getConnection() throws SQLException {
        //从连接池返回一个和数据库的链接
        return ds.getConnection();
    }

    //关闭连接
    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}

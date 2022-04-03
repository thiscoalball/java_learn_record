package utils;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//工具类 完成MySQL连接和关闭资源
public class JDBCUtils {
    //定义四个属性 因为只需要一份所以将其做成静态的变量
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    //在静态代码块初始化
    static {

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //连接数据库返回Connection
    public static Connection getConnection() throws SQLException {

            return DriverManager.getConnection(url, user, password);

    }

    //关闭相关资源

    /**
     * 1 ResultSet
     * 2 Statement 或者 PreparedStatement
     * 3 Connection
     * 4 如果需要关闭资源就传入对象，不需要就传null
     */
    public static void close(ResultSet set, Statement statement, Connection connection) throws Exception {
        //判断是否为空

        if (set != null) {
            set.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

    }
}

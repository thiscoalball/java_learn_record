package myJdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Homework01 {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        //为什么分开一条条执行就可以 合在一个字符串里就不行
//      String sql = "CREATE TABLE news(\n" +
//                "\tid Int PRIMARY KEY AUTO_INCREMENT,\n" +
//                "\tcontent VARCHAR(32));\n" +
//                "\t\n" ;
//      String sql = "INSERT INTO news VALUES(null,'small news'),(null,'middle news'),(null,'little news'),(null,'large news'),(null,'big news');\n";
//      String sql = "UPDATE news SET content = 'a new news' WHERE id = 1;\n";
        String sql = "DELETE FROM news WHERE id = 5;";

        statement.executeUpdate(sql);
    }
}

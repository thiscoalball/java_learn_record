package myJdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc01 {
    public static void main(String[] args) throws SQLException {
        //1 注册驱动
        Driver driver = new Driver();

        //2 获取链接       				得到Connection
        String url = "jdbc:mysql://localhost:3306/czy_db02?characterEncoding=utf-8";//localhost可以替换为ip地址
        Properties properties = new Properties();        //将用户名和密码放入Properties对象里
        properties.setProperty("user","root");//user和password是规定好的
        properties.setProperty("password","123456");//后面的值你根据实际情况写就可以
        Connection connect = driver.connect(url, properties);

        //3 执行增删改查				发送Sql语句给mysql执行

        String sql = "INSERT INTO actor VALUES(null,'周星驰','男','1988-11-11','22222')" +
                ",(null,'小龙女','女','2002-11-11','3333')";

        Statement statement = connect.createStatement();//帮我们发送静态sql语句
        int rows = statement.executeUpdate(sql);
        System.out.println(rows > 0 ? "成功":"失败");
        //4 释放资源						关闭相关连接
        statement.close();
        connect.close();
    }
}

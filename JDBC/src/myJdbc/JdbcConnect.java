package myJdbc;

import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnect {
    public static void main(String[] args) throws SQLException {


    }

    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/czy_db02?characterEncoding=utf-8";//localhost可以替换为ip地址
        Properties properties = new Properties();        //将用户名和密码放入Properties对象里
        properties.setProperty("user", "root");//user和password是规定好的
        properties.setProperty("password", "123456");//后面的值你根据实际情况写就可以
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    @Test
    public void connect02() throws Exception {
        //使用反射加载 动态加载更加灵活
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/czy_db02?characterEncoding=utf-8";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);

    }

    //方式3 用DriverManager来进行统一管理
    @Test
    public void connect03() throws Exception {
        //使用反射加载
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        //创建url和 user，password
        String url = "jdbc:mysql://localhost:3306/czy_db02?characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        //注册Driver驱动
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //方式4 Class.forName 自动完成注册驱动   这种方式用的最多 推荐使用这个
    @Test
    public void connect04() throws Exception {
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/czy_db02?characterEncoding=utf-8";
        String user = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    //方式5 通过配置文件读取 更加灵活
    @Test
    public void connect05() throws Exception{
        //通过配置文件得到对象信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
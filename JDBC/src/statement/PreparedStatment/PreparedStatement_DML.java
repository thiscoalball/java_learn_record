package statement.PreparedStatment;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_DML {
    public static void main(String[] args) throws Exception {

        //让用户输入
        String name = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新闻消息");
        String content = scanner.nextLine();


        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        //添加一个记录
        String sql = "insert into news values(null,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给问号赋值
        preparedStatement.setString(1, content);

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("成功");
        }

        preparedStatement.close();
        connection.close();
    }
}

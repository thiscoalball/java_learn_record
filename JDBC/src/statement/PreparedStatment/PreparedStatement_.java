package statement.PreparedStatment;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_ {
    public static void main(String[] args) throws Exception {

        //让用户输入
        String name = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要查询的人的名字");
        name = scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        //问号是一个占位符
        String sql = "select name,id from actor where name =?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给问号赋值
        preparedStatement.setString(1,name);
        //注意这个里面不能再填sql了 否则出事   因为前面已经处理过了
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("查找成功");
        }else {
            System.out.println("查无此人");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}

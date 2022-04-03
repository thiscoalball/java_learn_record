package statement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Statement_ {
    public static void main(String[] args) throws Exception{
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

        Statement statement = connection.createStatement();

        String sql = "select name from actor where name= ' " + name + " ' ;";
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){//如果查询到说明存在
            System.out.println("查询成功");
        }else {
            System.out.println("查无此人");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

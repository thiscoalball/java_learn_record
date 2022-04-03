package statement.PreparedStatment;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class homework01 {
    public static void main(String[] args) throws Exception {


        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        //String sql = "create table admin(userName varchar(32)) ";
        //String sql = "insert into admin values('b'),('c'),('d'),('e'),('tom')";
        //String sql = "update admin set userName = 'king' where userName = 'tom'";
        //String sql = "DELETE FROM admin WHERE userName = 'b'";
//        int i = connection.prepareStatement(sql).executeUpdate();
//        if(i>0){
//            System.out.println("成功");
//        }

        String sql = "select * from admin ";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
        connection.close();
    }
}

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
public class resultSet {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        //用配置文件加载数据库
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();
        String sql = "select id,name,sex,borndate from actor";
        //获取结果集
        ResultSet resultSet = statement.executeQuery(sql);
        //使用while取出数据
        while(resultSet.next()){
            int id = resultSet.getInt(1); //该行第一列
            String name = resultSet.getString(2);//该行第二列
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }

        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

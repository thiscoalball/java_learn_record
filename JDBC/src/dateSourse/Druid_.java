package dateSourse;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class Druid_ {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));

        //创建一个德鲁伊连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("德鲁伊的速度:"+ (l1-l)     );
    }
}

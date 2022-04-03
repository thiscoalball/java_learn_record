package Properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Properties02 {
    public static void main(String[] args) throws IOException {
        //使用Properties类读取文件
        Properties properties = new Properties();
        //加载到对象中
        properties.load(new FileReader("src\\mysql.properties"));
        //显示
        properties.list(System.out);
        //指定得到
        System.out.println();
        System.out.println(properties.getProperty("ip"));
        System.out.println(properties.getProperty("pwd"));

        properties.setProperty("age","12");
        properties.store(new FileWriter("src\\mysql.properties"),null);

    }
}

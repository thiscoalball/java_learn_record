package Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Properties01 {
    public static void main(String[] args) throws IOException {
        //读取mysql.properties文件并得到对应的ip
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\mysql.properties"));

        String line = " ";
        while((line = bufferedReader.readLine()) != null){
            String[ ] split = line.split("=");
            //如果要指定ip值的话
            if("ip".equals(split[0]))//这样比较麻烦 所以我们用Properties
            System.out.println(split[0]+"的值为" + split[1]);
        }
        bufferedReader.close();
    }
}

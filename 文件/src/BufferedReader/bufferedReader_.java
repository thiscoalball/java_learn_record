package BufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class bufferedReader_ {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\java创建文件尝试\\bufferedReader.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = bufferedReader.readLine())!=null) {
            //该方法是按行读取文件 返回空的时候表示读取完毕
            System.out.println(line);
        }
        //关闭这个就行了 底层会自动关闭FileReader
        bufferedReader.close();
    }
}

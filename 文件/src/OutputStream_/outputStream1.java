package OutputStream_;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class outputStream1 {
    public static void main(String[] args) {

    }
    @Test
    public void wirteFile() throws IOException {
        //创建流对象
        String filePath = "C:\\java创建文件尝试\\output.txt";
        FileOutputStream fileOutputStream = null;

        //这种创建方式会覆盖原来的内容 如果不想的话 就在构造器后把append置为true
        //fileOutputStream  = new FileOutputStream(filePath);

        fileOutputStream  = new FileOutputStream(filePath,true);
        //写入一个字节
        fileOutputStream.write('b');
        //写入字符串
        String str = "哈哈哈哈哈";
        //字符串的getBytes方法可以把你的字符串转化成字节数组进行写入
        fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
        String str1 = "你好世界";
        //fileOutputStream.write(str1.getBytes(StandardCharsets.UTF_8),0,1);
        fileOutputStream.close();
    }

}


import inputStream.FileInputStream_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) throws IOException {

        //完成文件的拷贝图片、音乐
        //思路分析：1 创建文件的输入流 将文件输入java
        //        2 创建文件的输出流 将java里读的输出到指定的文件里
        String filePath = "C:\\Users\\czy\\Desktop" +
                "\\韩顺平 2021零基础学Java 【软件 资料 代码 笔记】" +
                "\\资料\\分享资料\\bg.jpg";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        fileInputStream = new FileInputStream(filePath);
        fileOutputStream = new FileOutputStream("C:\\java创建文件尝试\\马保国.jpg");

        byte[] buf = new byte[1024];
        int readLength = 0;
        while((readLength = fileInputStream.read(buf))!= -1){
            //读取到之后就写入到文件
            fileOutputStream.write(buf,0,readLength);//一定要这个方法
        }
        System.out.println("拷贝完成");
        //关闭输入输出流
        fileInputStream.close();
        fileOutputStream.close();
    }
}

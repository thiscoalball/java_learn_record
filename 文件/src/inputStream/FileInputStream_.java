package inputStream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStream_ {
    public static void main(String[] args) {

    }

    @Test
    //read方法
    public void ReadFile1() {
        String filePath = "C:\\java创建文件尝试\\hello.txt";

        FileInputStream fileInputStream = null;
        int read = 1;
        try {
            //创建了对象用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //文件末尾时会读到-1 //读到-1表示读取完毕 读的为int 显示的时候转化为char
            while ((read = fileInputStream.read()) != -1) {
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件 释放文件连接
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();//下次我直接把异常抛走算了 不捕获了 代码里很烦
            }
        }

    }


    @Test
    //read(byte[] b)方法
    public void ReadFile2() {
        String filePath = "C:\\java创建文件尝试\\hello.txt";

        FileInputStream fileInputStream = null;
        byte[] buffer = new byte[50];//一次读取八个字节
        int readLength = 0;
        try {
            //创建了对象用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //文件末尾时会读到-1 //读到-1表示读取完毕 读的为int 显示的时候转化为char
            while ( (readLength =fileInputStream.read(buffer))!= -1) {
                System.out.print(new String(buffer,0,readLength));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭文件 释放文件连接
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();//下次我直接把异常抛走算了 不捕获了 代码里很烦
            }
        }

    }
}
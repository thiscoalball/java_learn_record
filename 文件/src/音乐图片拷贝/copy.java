package 音乐图片拷贝;

import java.io.*;

public class copy {
    public static void main(String[] args) throws IOException
    {
        String srcFile = "C:\\java创建文件尝试\\bg.jpg";
        String destFile = "C:\\java创建文件尝试\\copy\\bg.jpg";

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        byte[] buff = new byte[1024];
        int readLen = 0;
        while((readLen = bis.read(buff))!= -1){
            bos.write(buff,0,readLen);
        }
        bis.close();
        bos.close();
    }
}

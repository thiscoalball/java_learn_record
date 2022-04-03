package transforamation;

import java.io.*;

public class CodeQuestion {
    public static void main(String[] args) throws IOException {
        //1 创建字符输入流
        //默认情况下读取文件是按照utf-8编码 如果编码发生问题了、
        // 不是utf-8很容易出现乱码
        String filePath = "C:\\java创建文件尝试\\copy.txt";
        InputStreamReader isr = new InputStreamReader
                (new FileInputStream(filePath),"gbk");
        //放入包装流
        BufferedReader bufferedReader = new BufferedReader(isr);
        String s = bufferedReader.readLine();
        System.out.println(s);

        bufferedReader.close();
    }
}

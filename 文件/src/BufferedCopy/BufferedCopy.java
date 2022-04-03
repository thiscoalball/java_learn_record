package BufferedCopy;

import java.io.*;

public class BufferedCopy {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\java创建文件尝试\\story.txt";
        String destPath = "C:\\java创建文件尝试\\copy.txt";

        //注意 这两个都是字符操作 不能操作二进制和文件 可能造成文件损坏
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destPath,true));

        String line ;

        while((line = br.readLine())!=null){
            bw.write(line);
            bw.newLine();//每读一行就插入一个换行符
        }
        bw.close();
        br.close();
    }
}

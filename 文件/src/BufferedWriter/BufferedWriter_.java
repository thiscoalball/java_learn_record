package BufferedWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriter_ {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\java创建文件尝试\\buffered.txt"));
        bw.write("哈哈哈");
        bw.newLine();//插入一个换行
        bw.write("换行成功");
        bw.close();
    }
}

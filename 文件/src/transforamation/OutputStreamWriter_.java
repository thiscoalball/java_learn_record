package transforamation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\java创建文件尝试\\lalala.txt";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "gbk");

        osw.write("hahaha");
        osw.close();
    }
}

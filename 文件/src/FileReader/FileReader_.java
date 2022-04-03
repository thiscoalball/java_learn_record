package FileReader;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReader_ {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\java创建文件尝试\\story.txt";
        FileReader fileReader = new FileReader(filePath);
        //开始读取
        int data = ' ';
        while( (data = fileReader.read()) != -1){
            System.out.print((char)data);
        }
        fileReader.close();
    }
    @Test
    public void read() throws IOException {
        String filePath = "C:\\java创建文件尝试\\story.txt";
        FileReader fileReader = new FileReader(filePath);
        //开始读取
        char[] chars = new char[8];
        int readlength = 0;
        while( (readlength = fileReader.read(chars)) != -1){
            System.out.print(new String(chars,0,readlength));
        }
        fileReader.close();
    }
}

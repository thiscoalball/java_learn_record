package FileWriter;
import java.io.FileWriter;
import java.io.IOException;

class FileWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\java创建文件尝试\\FileWriter.txt";
        FileWriter fileWriter = new FileWriter(filePath);

        char[] chars = {'A','B','C'};

        fileWriter.write('D');
        fileWriter.write(chars);
        fileWriter.write("EFGH".toCharArray(),0,4);
        fileWriter.write("IJKLMN");
        fileWriter.write("MNOPQ",2,3);
        fileWriter.close();


    }
}

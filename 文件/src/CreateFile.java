import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) {

    }
    @Test
    public void create01() {
        String filePath = "C:\\java创建文件尝试\\创建文件1.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void create02() {
        File fileParent = new File("C:\\java创建文件尝试\\");
        String fileName = "创建文件2.txt";
        File file = new File(fileParent, fileName);
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer integer = new Integer(1);

    }
    @Test
    public void create03(){
        String parentPath = "C:\\java创建文件尝试\\";
        String filePath = "创建文件3.txt";
        File file = new File(parentPath, filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getName());
    }
}



import org.junit.jupiter.api.Test;

import java.io.File;

public class FileInformation {
    public static void main(String[] args) {
    }

    @Test
    public void info(){
        //创建文件对象
        File file = new File("C:\\java创建文件尝试\\创建文件1.txt");

        //调用方法得到的对应信息
        System.out.println(file.getName());

        System.out.println(file.getAbsoluteFile());

        System.out.println(file.getParentFile());

        System.out.println(file.length());//文件字节大小

        System.out.println(file.exists());

        System.out.println(file.isFile());

        System.out.println(file.isDirectory());
    }
}

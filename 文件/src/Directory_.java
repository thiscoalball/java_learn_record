import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.Reader;


public class Directory_ {
    public static void main(String[] args) {

    }
    @Test
    //删除文件
    public void m1(){
        String filePath = "C:\\java创建文件尝试\\创建文件1.txt";
        File file = new File(filePath);
        if(file.exists()){
            if(file.delete()){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else{
            System.out.println("该文件不存在");
        }
    }
    //判断目录是否存在 这里我们要意识到在java里目录也是作为文件存在的
    @Test
    public void m2(){
        String filePath = "C:\\java创建文件尝试";
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("该目录存在");
        }
    }
    //判断目录是否存在 存在就提示已经存在，不存在就创建
    @Test
    public void m3(){
        String filePath = "C:\\java创建文件尝试\\不存在就创建";
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("该目录存在");
        }else{
            if(file.mkdir()){//多级目录的话要用mkdirs
                System.out.println("创建成功");
            }else{
                System.out.println("创建失败");
            }
        }
    }
}

package ObjectInputStream;
//import ObjectOutputStream.Dog;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStream_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //指定文件路径
        String filepath = "C:\\java创建文件尝试\\ObjectOutputStream.txt";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath));
        //反序列化的顺序需要和序列化的顺序一致
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readUTF());
        Object dog = ois.readObject();
        System.out.println(dog.getClass());
        System.out.println(dog);

        //如果希望使用Dog的方法 需要向下转型
        //然后把Dog定义拷贝到可以引入的地方
        ois.close();

        //Dog dog = (Dog)dog;

    }
}


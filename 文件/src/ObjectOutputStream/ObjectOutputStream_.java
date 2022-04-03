package ObjectOutputStream;

import java.io.*;

public class ObjectOutputStream_ {
    public static void main(String[] args)  throws IOException {
        String filepath = "C:\\java创建文件尝试\\ObjectOutputStream.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));


        oos.writeInt(100);// int -> Integer（Integer实现了Serializable）
        oos.writeBoolean(true);//boolean --> Boolean
        oos.writeChar('a');//char -> Character
        oos.writeUTF("哈哈哈");//String
        oos.writeObject(new Dog("小黄",12));
        oos.close();
    }
}

class Dog implements Serializable {
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
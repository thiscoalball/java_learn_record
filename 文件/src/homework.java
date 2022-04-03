import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

public class homework {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src\\Dog.properties"));
        properties.list(System.out);
        String name = properties.getProperty("name");
        int age = Integer.parseInt( properties.getProperty("age"));
        String color = properties.getProperty("color");

        Dog dog = new Dog(age, name, color);
        System.out.println(dog);

        String filepath = "C:\\java创建文件尝试\\dog.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath));
        oos.writeObject(dog);
        oos.close();
    }
    @Test
public void test() throws IOException, ClassNotFoundException {
        String filePath = "C:\\java创建文件尝试\\dog.dat";
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));

        Dog dog = (Dog)objectInputStream.readObject();
        System.out.println(dog);
    }
}
class Dog implements Serializable {
    private int age;
    private String name;
    private String color;

    public Dog(int age, String name, String color) {
        this.age = age;
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
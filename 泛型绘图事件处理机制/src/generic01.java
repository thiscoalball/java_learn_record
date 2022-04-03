import java.util.ArrayList;

public class generic01 {
    public static void main(String[] args) {
        //使用传统方法解决
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Dog("a",10));
        arrayList.add(new Dog("b",5));
        arrayList.add(new Dog("c",15));

        //假如程序员不小心加入了一只猫进去，就不太好
        // 所以传统问题是 没有对ArrayList中的数据类型进行约束（不安全）
        //而且我们遍历的时候需要进行类型转换 如果集合中的数据量较大很影响效率
        for(Object o:arrayList){
            Dog dog = (Dog)o;
            System.out.println(dog);
        }
        //泛型演示
        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("aaa",12));
        dogs.add(new Dog("bbb",2));
        dogs.add(new Dog("ccc",7));
        //这种情况下其他类就已经加不进去了 而且遍历的时候可以直接取出Dog类型而不是Object类
        for(Dog dog:dogs){
            System.out.println(dog.getName() + ":" + dog.getAge());

        }

    }
}

class Dog{
    private String name;
    public int age;
    public Dog(String name,int age){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
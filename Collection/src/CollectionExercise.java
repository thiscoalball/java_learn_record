import java.util.ArrayList;
import java.util.Iterator;

public class CollectionExercise {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Dog("taidi",1));
        list.add(new Dog("jinmao",2));
        list.add(new Dog("samoye",3));

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }
        System.out.println("增强for迭代");
        for(Object d:list)
        {
            System.out.println(d);
        }
    }
}



class Dog{
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return
                "name=" + name  +
                " age=" + age ;
    }
}

import java.util.*;

public class GenericExercise {
    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<Student>();
        students.add(new Student("a",12));
        students.add(new Student("b",18));
        students.add(new Student("c",32));
        //students.add(100);使用泛型后该函数报错
        Iterator<Student> iterator = students.iterator();
        System.out.println("迭代器遍历");
        while (iterator.hasNext()) {
            Student next =  iterator.next();
            System.out.println(next.getName() + "---" + next.getAge());
        }
        System.out.println("增强for遍历");
        for(Student student:students){
            System.out.println(student);
        }
        HashMap<String, Student> stringStudentHashMap = new HashMap<String, Student>();

        stringStudentHashMap.put("aaa",new Student("aaa",12));
        stringStudentHashMap.put("bbb",new Student("bbb",32));

        Set<Map.Entry<String, Student>> entries = stringStudentHashMap.entrySet();
        Iterator<Map.Entry<String, Student>> iterator1 = entries.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, Student> next =  iterator1.next();
            System.out.println(next.getKey() + "---" + next.getValue());
        }

        Set<String> strings = stringStudentHashMap.keySet();
        System.out.println(strings);

    }
}

class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

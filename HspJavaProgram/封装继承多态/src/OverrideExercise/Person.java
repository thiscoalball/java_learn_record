package OverrideExercise;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String say()
    {
        return "名字：" + name + " 年龄:" + age;
    }

}

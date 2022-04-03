package com.reflection;

import java.lang.reflect.Field;

public class ReflectAccessProperty {

    public static void main(String[] args) throws Exception{

        Class<?> stuClass = Class.forName("com.reflection.Student");
        Object o = stuClass.newInstance();
        //System.out.println(o.getClass());
        Field age = stuClass.getDeclaredField("age");
        age.setAccessible(true);//进行爆破
        age.set(o,88);
        System.out.println(o);

        Field name = stuClass.getDeclaredField("name");
        name.set(null,"chenchen");//静态属性类名可以写为空
        System.out.println(o);
    }

}






class Student{
    private int age;
    public static String name;

    public Student (){}

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age + "   name=" + name +
                '}';
    }
}
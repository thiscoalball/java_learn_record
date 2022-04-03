package com.reflection;

import java.lang.reflect.Constructor;

public class ReflectionCreateInstance {
    public static void main(String[] args) throws Exception {
        //1 先获取到User类的Class对象
        Class<?> userClass = Class.forName("com.reflection.User");

        //2 通过public的无参构造器创建实例
        Object o = userClass.newInstance();
        System.out.println(o);

        //3 通过public的有参构造器创建实例
        Constructor<?> constructor = userClass.getConstructor(String.class);
        Object zhuang = constructor.newInstance( "zhuang");
        System.out.println(zhuang);

        //4 通过private的有参构造器创建实例
        Constructor<?> declaredConstructor = userClass.getDeclaredConstructor(int.class, String.class);
        declaredConstructor.setAccessible(true);//爆破
        Object marry = declaredConstructor.newInstance(20, "Marry");
        System.out.println(marry);
    }
}
class User{
    private int age = 10;
    private String name = "chen";

    public User(){}//公有的无参构造

    private User(int age, String name) {//私有的有参构造
        this.age = age;
        this.name = name;
    }

    public User(String name) {//公有的有参构造
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
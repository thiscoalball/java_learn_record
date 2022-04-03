package com.reflection;

import java.lang.reflect.Method;

public class ReflectionAccessMethod {
    public static void main(String[] args) throws Exception{
        Class<?> bossClass = Class.forName("com.reflection.Boss");
        Object o = bossClass.newInstance();
        Method hi = bossClass.getDeclaredMethod("hi",String.class);
        hi.invoke(o,"你好呀");

        Method say = bossClass.getDeclaredMethod("say", int.class, String.class, char.class);
        say.setAccessible(true);
        Object invoke = say.invoke(o, 1, "什么什么", 'A');
        System.out.println(invoke);
    }
}

class Boss{
    private int age;
    private static String name;

    public Boss(){}

    private static String say(int n,String s,char c){
        return n + " " + s + " " + c;
    }

    public void hi(String s){
        System.out.println("hi " + s);
    }
}

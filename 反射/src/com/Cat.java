package com;

public class Cat {
    private String name = "喵喵";
    public int age =10;
    public void hi(){
        System.out.println("哈哈哈");
    }
    public void cry(){
        System.out.println("哭哭");
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat(){}
}

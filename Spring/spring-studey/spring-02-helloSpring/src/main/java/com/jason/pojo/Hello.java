package com.jason.pojo;

public class Hello {
    private String name;

    //Spring使用无参构造 由于我们这里没有用其他构造覆盖默认无参构造所以不用写构造器
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("Hello " + name);
    }
}

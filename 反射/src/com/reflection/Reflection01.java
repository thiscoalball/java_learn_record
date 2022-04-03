package com.reflection;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class Reflection01 {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        //利用反射机制解决
        //1 先加载一个Class类
        Class aClass = Class.forName(classfullpath);
        //2 通过aClass类对象创建得到你加载的类
        Object o = aClass.newInstance();
        System.out.println(o.getClass());
        //3 通过这个类得到你加载的类的方法对象
        //     即 在反射机制中 可以把方法当作对象（万物皆对象）
        Method method1 = aClass.getMethod(methodName);
        //通过方法对象实现调用方法
        method1.invoke(o);
        //传统方法: 对象.方法()       反射机制中:方法.invoke(对象)
        //最重要的点在于 我们只需要该配置文件就可以改变程序的功能、

        //Field 成员变量
        //getField不能得到私有的属性
        //aClass.getField("name");
        Field age = aClass.getField("age");//反射是反过来的 在方法中写入对象获得至
        System.out.println(age.get(o));

        Constructor constructor = aClass.getConstructor(String.class,int.class);
        System.out.println(constructor);

    }
}

package com.reflection.question;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectionQuestion {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //反射问题的引出

        //根据配置文件读取路径
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.println(classfullpath);
        System.out.println(methodName);

        //创建对象
        //new classfullpath();//传统方法不行
        //通过外部文件配置在不修改源码的情况下控制持续 框架特别多这种 而且符合开闭原则

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
    }
}

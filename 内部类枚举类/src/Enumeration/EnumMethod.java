package Enumeration;

import java.lang.annotation.Retention;

public class EnumMethod {
    public static void main(String[] args) {
        //Enum类的常用方法
        Season3 autumn = Season3.AUTUMN;
        System.out.println(autumn.name());//输出枚举对象名字
        System.out.println();
        System.out.println(autumn.ordinal());//输出该枚举对象的编号 在底下这是第四个 从0开始
        System.out.println();
        Season3[] values = Season3.values();  //返回Season3[]的数组 包含定义的所有枚举对象
        System.out.println("通过values取出枚举对象的数组");
        for(Season3 season3: values) { //增强for循环
            System.out.println(season3);
        }
        System.out.println();
        Season3 autumn1 = Season3.valueOf("AUTUMN");//用该字符串去枚举对象里查找 找到了就返回 找不到报错
        System.out.println("autumn= "+autumn1);
        System.out.println();
        System.out.println(Season3.AUTUMN.compareTo(Season3.SUMMER));
    }
}
//Enum类的各自方法

enum Season3{
    //多个对象的话中间用 , 间隔    枚举对象必须放在枚举类的行首
    SPRING ("春天","温暖"),
    WINTER ("冬天","寒冷"),
    SUMMER ("夏天","炎热"),
    AUTUMN ("秋天","凉爽");
    private String name;
    private String desc; //属性放在后面


    private Season3(String name, String desc) {//构造器的参数必须在上面有对应实现
        this.name = name;
        this.desc = desc;
    }

    //2：去掉set相关的方法
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
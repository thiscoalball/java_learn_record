package Enumeration;

public class Enumeration01 {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SPRING);
    }
}
//对于季节而言 它的对象是固定的四个 不会有更多   所以使用枚举类
//把具体对象一个一个的列举出来的类称为枚举类

class Season{
    private String name;
    private String desc;

    //3：在season内部创建固定的对象(加上final的话可以加速运行速度
    public final static Season SPRING = new Season("春天","温暖");
    public final static Season WINTER = new Season("冬天","寒冷");
    public final static Season SUMMER = new Season("夏天","炎热");
    public final static Season AUTUMN = new Season("秋天","凉爽");

    //1：构造器私有化 防止直接new
    private Season(String name, String desc) {
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
package Enumeration;

public class Enumeration02 {
    public static void main(String[] args) {
        System.out.println(Season2.SUMMER);
    }
}

enum Season2{
   //多个对象的话中间用 , 间隔    枚举对象必须放在枚举类的行首
    SPRING ("春天","温暖"),
    WINTER ("冬天","寒冷"),
    SUMMER ("夏天","炎热"),
    AUTUMN ("秋天","凉爽");
    private String name;
    private String desc; //属性放在后面


    private Season2(String name, String desc) {//构造器的参数必须在上面有对应实现
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
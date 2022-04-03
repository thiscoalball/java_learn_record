package 饿汉式;

//步骤如下
//1：构造器私有化 -> 防止直接new度睇下
//2：类的内部创建对象
//3：向外暴露一个静态的公共方法
//4：代码实现
public class SingleTon01 {
    public static void main(String[] args) {
        GirlFriend gf = GirlFriend.getGf();
        System.out.println(gf);
        GirlFriend gf2 = GirlFriend.getGf();
        System.out.println(gf2);
        System.out.println(gf == gf2);
    }
}

//有一个类 GirlFriend 只能有一个女朋友哈哈哈 使用单例设计模式
class GirlFriend{
    private String name;

    // 2:在内部直接创建一个GirlFriend对象//为了能在静态方法中使用 将其修饰为static
    //问题：饿汉式可能造成了创建了对象 但是没有使用
    private  static GirlFriend gf = new GirlFriend("dog");
    // 1:将构造器私有化
    private GirlFriend(String name) {//不改权限的话可以new好多个女朋友 private
        this.name = name;
    }
    // 3:提供一个公共的静态方法 返回gf对象
    public static GirlFriend getGf(){
        return gf;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                '}';
    }
}

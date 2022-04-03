package Interface;

public class ExtendVsInterface {
    public static void main(String[] args) {
        littleMonkey littlemonkey = new littleMonkey("小猴子");
        littlemonkey.climb();
        littlemonkey.swimming();
        littlemonkey.flying();
    }
}
class Monkey{
    private String name;

    public Monkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void climb(){
        System.out.println(name+"会爬树");
    }
}
//接口
interface FishAble{
    void swimming();
}
interface BirdAble{
    void flying();
}
//继承
class littleMonkey extends Monkey implements FishAble,BirdAble{
    public littleMonkey(String name) {
        super(name);
    }

    @Override
    public void swimming() {
        System.out.println(getName()+"通过接口 学会像鱼一样游泳");
    }

    @Override
    public void flying() {
        System.out.println(getName()+ "通过接口 学会像鸟一样飞翔");
    }
}
//继承vs接口      你只能有一个爸  但可以有多个师傅
package Abstract;

public class Abstract01 {
    public static void main(String[] args) {

    }
}
abstract class Animal{//声明为抽象类
    //抽象类不能实例化
    //抽象类不一定要包含abstract方法

    //包含abstract方法  则该类一定要声明abstract
    //abstract只能修饰类和方法 不能修饰属性和其他的

    //抽象类本质还是类 可以有任意成员（非抽象方法，构造器，静态属性等等）
    //如果一个类继承了抽象类 必须实现抽象类的所有抽象方法 除非它自身也声明为抽象类

    //抽象方法不能用private final 和 static修饰 因为这些关键字都是和重写相违背的
    private String name;

    public Animal(String name) {
        this.name = name;
    }
//    public void eat(){
//        //对于不确定的动物来说不知道要吃什么 只能瞎写一句话然后让继承的具体的动物重写这个方法
//        //考虑用抽象方法去实现
//    }

    //注意 这里的抽象方法不能加{}方法体   就像是c++的纯虚函数
    public abstract void eat();//这里写完之后 你的类也要声明为抽象类 具体方法靠子类实现
}
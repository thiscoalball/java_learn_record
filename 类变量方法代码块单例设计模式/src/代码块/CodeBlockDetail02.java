package 代码块;

public class CodeBlockDetail02 {
    public static void main(String[] args) {
        A a = new A();
    }
}
//优先级 1 - 2 - 3
//1：调用静态代码块和静态属性初始化   他们俩优先级一样 按照定义的顺序调用
//2：普通代码块和普通属性初始化   优先级一样 多个的话按照的顺序调用
//3：构造器

//当存在继承关系的时候
//先往上找父类 当把父和子的静态代码块和静态属性全部执行完的时候
//执行父类的普通代码块和普通属性  然后父类的构造方法
//然后子类的普通代码块普通属性 子类的构造
class A{
    private static int n1 = getN1();
    static{
        System.out.println("A的静态代码块01");
    }
    public static int getN1()
    {
        System.out.println("getN1静态方法被调用");
        return 100;
    }
}
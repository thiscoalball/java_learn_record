package Final;

public class FinalDetail01 {

}

//final修饰的的属性又叫常量 一般用XX_XX_XX 来命名（大写和下划线）
//final修饰的属性非静态时：定义时必须先付初值 并且以后不能再修改   赋值可以在如下位置
//    1：定义时  2：构造器中 3：代码块中

//如果final修饰的属性是静态的 ：则初始化的位置只能说
//    1：定义时 2：静态代码块中  不能在构造器里
//final类不能继承 但是可以实例化对象
//如果类表示final类 但是含有final方法 则该方法虽然不能重写 但是可以被继承 就是不能改变 但是子类能用

//final类不能修饰构造方法
//final和static往往一起使用效率更高 底层编译器做了优化
//包装类（Integer，Double，Float，String）这些的都是final
class AA{
    //修饰的属性非静态
    public final double TAX_RATE = 0.08; //true;

    public final double TAX_RATE02;

    public final double TAX_RATE03;

    public AA(){//在构造器中
        TAX_RATE02 = 1.1;
    }                      //true

    {//代码块中 //静态代码块不行
        TAX_RATE03 = 0.01;
    }
}

class AAA{
    //修饰的属性非静态
    public final double TAX_RATE = 0.08; //true;

//    public final double TAX_RATE02;

    public static final double TAX_RATE03; //静态变量在静态代码块中

//    public AA(){//在构造器中静态的不行
//        TAX_RATE02 = 1.1;
//    }                      //true

    static
    {//代码块中 //静态代码块不行
        TAX_RATE03 = 0.01;
    }
}


package Final;

public class Final01 {
    public static void main(String[] args) {
//        E e = new E();
//        e.Rate = 1;
    }
}
//1：当不希望类被继承的时候 可以用final修饰
//2：当不希望父类的某个方法被子类重写和覆盖/重写的时候 可以用final修饰 （权限符 final 返回类型 函数名）
//3：当不希望类的某个属性被修改的时候 可以用final修饰
//4：当不希望某个局部变量被修改的时候


//此时如果给A加个final B就不能继承
final class A{

}
//class B extends A{ }

class C{
    public final void hi(){}//加上final 底下就不能重写它了
}
//class D extends C{
//    @Override
//    public void hi() {
//        super.hi();
//    }
//}

//当不希望类的某个属性被修改的时候 final修饰
class E{
    public final double Rate = 0.08;//去看主程序注释

}

//局部变量不能修改时
class F{
    final double NUM = 0.01;
    //NUM = 0.09
}

package InnerClass;

public class LocalInnerClass {
    public static void main(String[] args) {
        Outer02 outer02 = new Outer02();
        outer02.m1();
    }
}

//局部内部类是定义在外部类的局部位置的 比如方法中或者代码块中 一般在方法中 并且有类名
//1：可以直接访问外部类的所有成员 包括私有的
//2：不能添加访问修饰符，因为他就是一个局部变量 不配。但是可以用final
//3：作用域：仅仅再定义它的方法或者代码块中
//4：局部内部类访问外部类的成员  ----->直接访问
//5：外部类访问局部内部类的成员  ----->先创建对象 再访问（必须在作用域内）
//6：外部其他类               ----->不能访问局部内部类（局部变量）
//7：外部类和局部内部类的成员重名时 遵循就近原则访问
        // 如果要访问外部类的成员 使用 外部类名.this.成员

class Outer02{
    private int n1 = 100;
    private void m2(){
        System.out.println("Outter02 m2()");
    };
    public void m1()
    {
        class Inner02//局部内部类
        {
            public void f1()
            {
                System.out.println("n1=" + n1);//直接访问私有属性
                m2();
            }
        }
        class Inner03 extends Inner02{ }//本质是类 所以完全可以继承 除非final修饰
        //外部类在方法中可以直接创建Inner02对象 调用方法即可
        Inner02 inner02 = new Inner02();
        inner02.f1();
    }
}
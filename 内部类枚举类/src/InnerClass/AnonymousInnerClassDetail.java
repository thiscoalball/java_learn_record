package InnerClass;

public class AnonymousInnerClassDetail {
    public static void main(String[] args) {
        Outer05 outer05 = new Outer05();
        outer05.f1();
    }
}
//如果匿名内部类和外部类的成员重名的时候 匿名内部类访问时
//遵循就近原则   如果想访问外部类的成员 则可以使用 （外部类.this.成员）去访问
class Outer05{
    private int n1 = 99;
    public void f1(){
        //由于匿名对象的特殊机制 有以下两种调用方法

        //第一种
        Person p = new Person(){
            @Override
            public void hi() {
                System.out.println("匿名内部类第一种调用方法重写了hi");
            }
        };
        p.hi();

        //第二种
        new Person(){
            @Override
            public void hi() {
                System.out.println("匿名内部类第二种调用方法重写了hi");
            }
        }.hi();
    }
}
class Person{
    public void hi(){
        System.out.println("person hi()");
    }
}

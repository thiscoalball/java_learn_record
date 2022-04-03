package InnerClass;

public class StaticInnerClass {
    public static void main(String[] args) {
        Outer09 outer09 = new Outer09();
        outer09.m();

        //外部其他类使用静态内部类
        //方式1
        Outer09.Inner09 inner09 = new Outer09.Inner09();
        inner09.say();
        //方式2     写方法返回静态内部类的对象
        Outer09.Inner09 inner091= outer09.getInner09();
        inner091.say();
    }
}

class  Outer09{
    private int n1 = 10;
    private static String name = "张三";
    static class Inner09{//静态成员内部类 定义在外部类的成员位置 static修饰
        public  void say(){
            System.out.println(name);
            //System.out.println(n1);//不能访问非静态成员
        }
    }

    public void m(){
        Inner09 inner09 = new Inner09();
        inner09.say();
    }

    public Inner09 getInner09(){
        return new Inner09();
    }
}
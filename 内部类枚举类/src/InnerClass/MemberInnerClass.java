package InnerClass;

public class MemberInnerClass {
    public static void main(String[] args) {

        Outer08 outer08 = new Outer08();
        outer08.t1();
        System.out.println();
        //外部其他类使用成员内部类的三种方式
        //第一种方法
        Outer08.Inner08 inner08 = outer08.new Inner08();//记得先创建outer08
        inner08.say();

        //第二种方法   在外部类中编写一个方法返回inner的对象实例
        Outer08.Inner08 inner08instance = outer08.getInner08();
        System.out.println();
        inner08instance.say();//用返回的对象调用方法


    }

}

class Outer08{
    private int age =10;
    public String name = "张三";
    private void hi(){
        System.out.println("hi()方法");
    }
    //可以添加任意的访问修饰符因为它的地位就是个成员
    class Inner08{
        private int num = 10;
        public void say(){
            System.out.println("Outer08的age:" + age + " Outer08的name:"+ name);
            hi();//使用外部类的私有方法

        }
    }

    //外部类要使用内部类要创建成员内部类的对象然后使用相关的方法属性之类的
    public void t1(){
        Inner08 inner08 = new Inner08();
        inner08.say();
        hi();//使用外部类的私有方法
        System.out.println( inner08.num); //自己类的私有属性在这也可以调用
    }

    public Inner08 getInner08(){//直接返回创建的内部类对象
        return new Inner08();
    }
}
